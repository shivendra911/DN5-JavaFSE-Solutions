# Docker Exercise — Containerizing the Account & Loan Microservices

This satisfies Module 13's learning objectives (Docker concept, basic commands,
storage/networking, orchestration) using the microservices you already built
in Module 8, since Cognizant didn't provide a separate hands-on file for Docker.

## Prerequisite
Install Docker Desktop: https://www.docker.com/products/docker-desktop/
Verify it's running:
```bash
docker --version
docker ps
```

---

## Part 1: Build and run ONE container manually (learn the basic commands first)

### Step 1: Copy account/pom.xml and src/ from Module 8's account project
into this `account/` folder (alongside the Dockerfile already provided).

### Step 2: Build the image
```bash
cd account
docker build -t account-service:1.0 .
```
`-t` tags the image with a name and version so you can refer to it later.

### Step 3: List images
```bash
docker images
```
You'll see `account-service` with tag `1.0`.

### Step 4: Run a container from the image
```bash
docker run -d -p 8080:8080 --name account-container account-service:1.0
```
- `-d` = detached mode (runs in background)
- `-p 8080:8080` = maps host port 8080 to container port 8080
- `--name` = gives the container a friendly name instead of a random one

### Step 5: List running containers
```bash
docker ps
```

### Step 6: Test it
```bash
curl http://localhost:8080/accounts/00987987973432
```
Should return the same JSON as when you ran it directly in IntelliJ.

### Step 7: View logs
```bash
docker logs account-container
```

### Step 8: Execute a command inside the running container
```bash
docker exec -it account-container sh
```
This drops you into a shell *inside* the container. Try `ls`, then `exit` to leave.

### Step 9: Stop and remove the container
```bash
docker stop account-container
docker rm account-container
```

### Step 10: Remove the image too (cleanup)
```bash
docker rmi account-service:1.0
```

---

## Part 2: Docker Compose — running BOTH services together

### Step 1: Copy loan/pom.xml and src/ into the loan/ folder the same way

### Step 2: From the root folder (where docker-compose.yml lives), run:
```bash
docker-compose up --build
```
`--build` forces it to rebuild images even if they already exist.

### Step 3: Verify both are running
```bash
docker ps
```
You should see both `account-service` and `loan-service` containers.

### Step 4: Test both
```bash
curl http://localhost:8080/accounts/00987987973432
curl http://localhost:8081/loans/H00987987972342
```

### Step 5: View the custom network created
```bash
docker network ls
docker network inspect docker-exercise_bank-network
```
Notice both containers are listed under the same network — this is what
lets them potentially call each other by container name instead of IP.

### Step 6: Stop everything
```bash
docker-compose down
```
This stops AND removes both containers plus the network in one command.

---

## Part 3: Storage concept (bonus, not required by objectives but good to know)

Containers are stateless by default — if you restart one, anything written
to its filesystem is lost. To persist data (e.g. for a database container),
you'd add a named volume:

```yaml
volumes:
  - account-data:/var/lib/data
```

This isn't needed for account/loan (they have no database), but it's worth
knowing for when Module 6/7's Spring Data JPA project gets containerized —
MySQL running in Docker absolutely needs a volume, or your data vanishes
every time the container restarts.

---

## Concepts checklist (matches Module 13's learning objectives)
- [x] Docker commands: run, ps, stop, rm, images, rmi, logs, exec
- [x] Docker images: building via Dockerfile, multi-stage builds, tagging
- [x] Docker Compose: multi-container orchestration, YAML config
- [x] Docker networking: custom bridge network, container-to-container communication
- [x] Docker storage: named volumes (concept covered, not required for this exercise)
