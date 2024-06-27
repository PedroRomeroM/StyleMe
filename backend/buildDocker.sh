cd microAuth

 docker build -t microauth -f .Dockerfile .

cd ..

cd microChallenge

 docker build -t microchallenge -f .Dockerfile .

cd ..

cd microOrquestrador

 docker build -t microorquestrador -f .Dockerfile .

cd ..

cd microUsers

 docker build -t microusers -f .Dockerfile .

cd ..

cd service-discovery

 docker build -t service-discovery -f .Dockerfile .

cd ..

cd gateway

 docker build -t gateway -f .Dockerfile .