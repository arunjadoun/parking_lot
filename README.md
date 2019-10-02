# parking_lot
**Steps to Run:**

 1. Unzip parking_lot.zip
 2. cd parking_lot
 3. run *bin/setup*
 4. run functional tests by hitting *bin/run_functional_tests*
 5. finally run by hitting *bin/parking_lot*
 6. Optionally run using file based input by hitting *bin/parking_lot your_path_to_readable_file*

 Classes Overview :

  Brief Parking Flow:
 *Wrap command in CommandWrapper object -> File/Interactive Executor Factory & create file/interactiveExecutor -> Basic Command validator factory & validate -> ComamndHandler factory creation -> Pass to handler -> Argument validation & more validation -> Parking related functional invoke*

 1. **ParkingLot**: Defines a parkingLot with certain number of spots to be occupied by parkable vehicles
 2. **Parkable**: An interface with a common type for all vehicles which can be parked. EX: A car can be parked and a truck might not be allowed to park because of certain space constraints and feasibility
 3. **Lead**: Any Parking/Unparking request always starts with creation of lead which has vehicle info along with Request Type
 4. **Vehicle**: Abstract class to represnt top level vehicles with color and registration number as a primary identity
 5. **Car**: A subtype of vehicle (Which can be parked in parkingLot)
 7. **Slot**: Class which define a slot to be occupied by parkable vehicle
 8. **ParkingLeadStatus**: Enums for all parkingLead states. Ex: PENDING, FAILED, PARKED, CLOSED
 9. **SlotStatus**: Enums for Slot status: RESERVED, OCCUPIED, VACANT, NOT_OPERATIONAL
 10. **Parkings**: Class containing parking related status/overview goes in this static group of methods.
 11. **ParkingTicket**: A ParkingTicket entity post successful parking which is being issued/returned

**Command Module (Wrapper + Validator + Executor + Handler):**

 12. **Command**: A wrapper for commands coming from client(console). which is composed of command and argument list. Ex: "park reg_1 Blue"
 13. **Commands**: Enum class to contain all valid commands Ex: create, park etc
 14. **CommandValidator**: A validator interface to be implemneted by all such command validators
 15. **CommandValidatorFactory**: A factory class for creating validator factory based on client supplied command
 16. **Handler**: A handler interface to handle command execution of all command handlers of this type
 17. **HandlerFactory**: A factory to create handler object based on command to execute thr functional flow
 18. **Executor**: Executor interface to execute filebased or interactive flows
 19. **ExecutorFactory**: Factory to create object based on command argument.
 20. **FileBasedExecutor**: Executes File based argument flow
 21. **InteractiveExecutor**: Interactive flow executor

