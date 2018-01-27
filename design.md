
### Subsystems
## Lift 
### Commands 
* MoveToPosition 
* Calibrate (optional)
* JoystickMovement
* MatchStart(optional) 
### Methods 
* void setSpeedsPercent(double speed)
* void zeroEncoders()
* double getEncoders() 
* boolean isAtUpperLimit() 
* boolean isAtLowerLimit()
## Cube Aquisition 
### Commands
* Lower
* Open
* Close
* Raise
* EjectCube
* PositionCube 
* WaitForCube 
* ToggleRaise 
* ToggleButtons(not confirmed) 
### Methods 
* void setspeedPercent(double, lspeed, double rspeed)
* void setRaised(boolean raised)
* boolean cubeDetected()
* boolean isOpen()
* void setPistonMode(pistonMode mode)
* PistonMode getPistonMode()
* boolean isRaised() (possibly) 
## Climbing 
### Commands 
* ReleaseRight
* ReleaseLeft 
* RaiseRight
* RaiseLeft
### Methods 
* boolean getLeftPosition() 
* boolean getRightPosition() 
* void setRightPosition(boolean position) 
* void setLeftPosition(boolean position)
## DriveTrain
### Methods
* ControlMode getControllerDriveMode()
* DriveGear getCurrentGear()
* double getkD()
* double getkF()
* double getkI()
* double getkP()
* int getLeftEncPos()
* int getLeftEncVelocity()
* double etRampRate()
* int getRightEncPos()
* int getRightEncVelocity()
* double getRobotYaw()
* void zeroRobotYaw()
* void initDefaultCommand()
* boolean isNavXPresent()
* void setCurrentControlMode()
* void setCurrentGear(DriveTrain.DriveGear gear)
* void setPID(double p, double i, double d, double f)
* void setRampRate(double rampRate)
* void setSpeedPercent(double lspeed, double rspeed)
* void setSpeedRaw(double lspeed, double rspeed)
### Commands 
* JoystickDrive

