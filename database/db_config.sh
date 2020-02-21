# Create network for using into cassandra image
# docker network create alta-network --driver bridge

# Create docker container
# docker run --name alta --network alta-network -d cassandra:3.11

# Start container
# docker start alta

# Connect to cassangra docker
# docker run -it --network alta-network --rm cassandra cqlsh alta

# Connect to shell
# docker exec -it alta bash

# Execute cql file
# cqlsh -f /home/db_schema.cql

docker cp ./db_schema.cql alta:/home