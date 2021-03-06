package org.team639.lib.commands;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A separate thread for running drive commands. After starting in RobotInit, access via {@link ThreadedDriveCommand}.
 * @see ThreadedDriveCommand
 * @see DriveCommand
 */
public class DriveThread extends Thread {

    private static DriveThread instance;

    // private ReadWriteLock defaultLock = new ReentrantReadWriteLock();
    private ReadWriteLock currentLock = new ReentrantReadWriteLock();

    private volatile ThreadedDriveCommand runner;
    // private volatile DriveCommand defaultCommand;
    private volatile DriveCommand currentCommand;

    /**
     * Returns a reference to the drive thread.
     * @return a reference to the drive thread.
     */
    public static DriveThread getInstance() {
        if (instance == null) {
            instance = new DriveThread();
        }
        return instance;
    }

    /**
     * Constructs a new drive thread
     */
    private DriveThread() {
        super("Drive Thread");
        this.setDaemon(true);
        // this.start() // Do I really want this? Should the user call start?
    }

    /**
     * Sets a default command to run when no command has been specified.
     * TODO: I'm not sure if this is actually necessary. Removed for the moment.
     */
    // public void setDefault(DriveCommand command) {
    //     defaultLock.writeLock().lock();
    //     try {
    //         if (defaultCommand != null) defaultCommand.interrupted();
    //         defaultCommand = command;
    //         defaultCommand.initialize();
    //     } finally {
    //         defaultLock.writeLock().unlock();
    //     }
    // }

    /**
     * Sets a drive command to be run.
     * @param command The DriveCOmmand to run.
     * @param parent The ThreadedDriveCOmmand calling this method (`this`).
     */
    public void runCommand(DriveCommand command, ThreadedDriveCommand parent) {
        currentLock.writeLock().lock();
        try {
            if (currentCommand != null) {
                System.out.println("You appear to have attempted to add a drive command without removing the last"
                        + " one with `interruptCommand()` or letting it finish on its own. Thankfully I had the foresight to"
                        + " print this message instead of just letting you (by which I probably mean future me) break everything.");
            } else {
                runner = parent;
                currentCommand = command;
                currentCommand.initialize();
            }
        } finally {
            currentLock.writeLock().unlock();
        }
    }

    /**
     * Returns if the current command is finished.
     * @return If the current command is finished.
     */
    public boolean isCommandRunning() {
        currentLock.readLock().lock();
        boolean running = false;
        try {
            running = currentCommand != null;
        } finally {
            currentLock.readLock().unlock();
        }
        return running;
    }

    /**
     * Interrupts the current command.
     */
    public void interruptCommand() {
        currentLock.writeLock().lock();
        try {
            if (currentCommand != null) {
                currentCommand.interrupted();
                currentCommand = null;
                runner.done();
                runner = null;
            }
        } finally {
            currentLock.writeLock().unlock();
        }
    }

    public void run() {
        while (true) {
            currentLock.writeLock().lock();
            // defaultLock.writeLock().lock();
            try {
                if (currentCommand != null) {
                    currentCommand.execute();
                    if (currentCommand.isFinished()) {
                        currentCommand.end();
                        currentCommand = null;
                        runner.done();
                        runner = null;
                    }
                } //else if (defaultCommand != null) {
                //     defaultCommand.execute();
                // } else {
                //     System.out.println("WHAT ARE YOU DOING SET A DEFAULT COMMAND!!! AAAAAAHHHHHHHHHHH!!!!!!!!!!!!");
                // }
            } finally {
                currentLock.writeLock().unlock();
                // defaultLock.writeLock().unlock();
            }
        }
    }
}

