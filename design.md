# Subsystems
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
