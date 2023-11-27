TCP server class
   is responsible for creating a socket which will accept 2 clients
   then it transmits config data to the client
   and displays information about the clients
   then receives messages from each client and relays them to another client

TCP client class
    connects to server, and receives config data
    it will display this information
    then send numMessages messages back to server
    and waits to receive additional messages

Places for improvement
    currently, only supports 2 clients
    (potentionally an additional "communication" class could be used to handle output and input streams for each client socket)