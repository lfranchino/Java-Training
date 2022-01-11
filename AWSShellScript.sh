#/bin/bash
gradle build
docker build -t matching-app:latest .
aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 032044580362.dkr.ecr.us-east-2.amazonaws.com
aws ecr batch-delete-image --repository-name best-repository --image-ids imageTag=latest --output text
docker tag matching-app:latest 032044580362.dkr.ecr.us-east-2.amazonaws.com/best-repository:latest
docker push 032044580362.dkr.ecr.us-east-2.amazonaws.com/best-repository:latest
#aws ssm send-command --instance-ids i-037f413943e1b202e --document-name "AWS-RunShellScript" --comment "authenticate again" --parameters commands "aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 032044580362.dkr.ecr.us-east-2.amazonaws.com"
aws ssm send-command --instance-ids i-037f413943e1b202e --document-name "AWS-RunShellScript" --comment "Stop any currently running containers " --parameters commands="docker stop \$(docker ps -q)" --region us-east-2 --output text
aws ssm send-command --instance-ids i-037f413943e1b202e --document-name "AWS-RunShellScript" --comment "Delete any images" --parameters commands="docker rmi -f 032044580362.dkr.ecr.us-east-2.amazonaws.com/matchingapp:latest" --region us-east-2 --output text
aws ssm send-command --instance-ids i-037f413943e1b202e --document-name "AWS-RunShellScript" --comment "pull new docker image" --parameters commands="docker pull 032044580362.dkr.ecr.us-east-2.amazonaws.com/best-repository" --region us-east-2 --output text
aws ssm send-command --instance-ids i-037f413943e1b202e --document-name "AWS-RunShellScript" --comment "Run container" --parameters commands="docker run -p 8080:8080 032044580362.dkr.ecr.us-east-2.amazonaws.com/best-repository:latest" --region us-east-2 --output text



