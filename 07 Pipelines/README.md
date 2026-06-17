CI/CD (Continuous Integration and Continuous Delivery/Deployment)
==============================================================================

    Continuous Integration (CI)
        CI is a development practice where developers frequently merge their code changes into a central repository (usually multiple times a day).

        The Problem it Solves: "Integration Hell"—where developers work in isolation for weeks and face massive conflicts when trying to merge their code.

        What Happens Automatically: 
            1.  A developer pushes code to Git.
            2.  An automated server triggers a Build (e.g., compiling Java code into a JAR, building a Docker image).
            3.  Automated Unit Tests run immediately.
            4.  Code Quality Scans (like SonarQube) check for bugs and security vulnerabilities.

        The Goal: Detect bugs early. If a test fails, the build "breaks," and the developer fixes it immediately.

    Continuous Delivery (CD)
        Continuous Delivery picks up where CI leaves off. It ensures that the code passing through the CI phase is always in a deployable state and ready to go to production at any moment.

        What Happens Automatically: The artifact (JAR, container, etc.) is deployed to staging, testing, or UAT (User Acceptance Testing) environments. Automated integration and end-to-end tests run here.

        The Catch: Deploying to the final production environment requires a manual human approval (clicking a button).

    Continuous Deployment (CD)
        Continuous Deployment takes automation to the absolute limit.

        The Difference: There is no manual approval.

        The Workflow: If a code change successfully passes every single testing phase in the pipeline, it is automatically deployed directly to the live production servers, visible to end-users within minutes of the developer pushing the code.

    CI/CD Pipeline

        A Pipeline is the logical sequence of steps (stages) that software must pass through from   conception to production. Think of it as an assembly line for code.

        Pipeline Stages

            1. Source	        Code is written and pushed to a version control system. 
                                Triggers the pipeline via webhooks.	
                                
                                Git, GitHub, GitLab, Bitbucket

            2. Build	        Source code is compiled. Dependencies are resolved. 
                                Artifacts or container images are created.	
                                
                                Maven, Gradle, npm, Docker

            3. Test	Run         Unit tests, Component tests, 
                                and Static Application Security Testing (SAST).	
                                
                                JUnit, Mockito, Jest, SonarQube

            4. Release	        The built artifact is stored in a secure registry, 
                                ready for deployment.	
                                
                                Nexus, Artifactory, Docker Hub, ECR

            5. Deploy	        Artifact is deployed to environments (Staging/Prod). 
                                Can involve blue-green or canary deployment strategies.	
                                
                                Kubernetes, Ansible, Terraform, AWS

            6. Monitor	        Post-deployment checks to ensure the application 
                                is healthy and performant.	
                                
                                Prometheus, Grafana, ELK Stack, Splunk

    CI/CD Tools Landscape
        Depending on your architecture (monolith, cloud-native, microservices), different tools fit different needs:

            Jenkins: The classic, open-source grandfather of CI/CD. Highly customizable with thousands of plugins, though it requires significant manual management and infrastructure overhead.

            GitHub Actions / GitLab CI: Modern, cloud-integrated solutions. They use YAML configuration files stored directly in our repository (Pipeline-as-Code), making version control of our deployment steps effortless.

            ArgoCD: A specialized tool designed for GitOps in Kubernetes ecosystems. It constantly monitors our Git repo and ensures our live Kubernetes cluster matches the state defined in our code.

            Tekton / AWS CodePipeline / Azure DevOps: Cloud-native managed pipeline services.

GitHub Actions
--------------------------------------------------------------------------------------------------------------------

    To create a GitHub Actions pipeline from scratch, we need to understand the structural hierarchy of its configuration file. GitHub Actions uses YAML format, which is strictly space-sensitive (always use spaces, never tabs).

    1. The Core Hierarchy of a Workflow File

        Every GitHub Actions file follows this exact structural sequence:

            # 1. The name of the pipeline as it appears in the GitHub UI
            name: Application CI Pipeline

            # 2. The Trigger Engine (When should this run?)
            on: 
                push:
                    branches: [ main ]

            # 3. Environmental Variables (Global constants)
            env:
                APP_NAME: my-cool-app

            # 4. The Execution Blocks (What are we doing?)
            jobs:
            
                # A custom Identifier for our first job
                compile-and-test:
                    # Where should this job execute?
                    runs-on: ubuntu-latest
                    
                    # The sequence of tasks inside this specific job
                    steps:
                    - name: Code Checkout
                        uses: actions/checkout@v4

                    - name: Run a Shell Command
                        run: echo "Building ${{ env.APP_NAME }}..."
    
    2. Deep Dive into the Structural Blocks

        Let's break down each structural component you need to write a valid pipeline.

        Block A: The Trigger Engine (`on:`)

            The `on` keyword defines the webhooks or conditions that wake up your pipeline. You can use simple strings, arrays, or highly filtered blocks.

            Simple Branch Triggers:
    
                on:
                    push:
                        branches: [ main, develop ] # Triggers when code is pushed to either branch

            Pull Request Activity Triggers:
                on:
                    pull_request:
                        types: [ opened, synchronized ] # Triggers when a PR is opened or new code is pushed to it
                        branches: [ main ]              # Only if the PR target is the main branch
            
            Manual Execution (`workflow_dispatch`):
                Adding this creates a visible "Run workflow" button in the GitHub Actions UI, allowing team members to trigger deployments manually without pushing code.
    
                on:
                    workflow_dispatch:
        
        Block B: The Job Container (`jobs:`)

            A single workflow can have multiple jobs. Crucial Rule: By default, if we define multiple jobs, they run concurrently (at the same time) on completely separate, isolated virtual machine runners.

            If our deployment job needs the build job to finish first, you must explicitly link them using the `needs` keyword:

            jobs:
                build-job:
                    runs-on: ubuntu-latest
                    steps:
                        - run: echo "Building artifact..."

                test-job:
                    runs-on: ubuntu-latest
                    needs: build-job # Waits for build-job to pass successfully
                    steps:
                        - run: echo "Testing artifact..."

                deploy-job:
                    runs-on: ubuntu-latest
                    needs: test-job # Waits for test-job to pass successfully
                    steps:
                        - run: echo "Deploying to production server..."
        
        Block C: The Execution Platform (`runs-on:`)

            We must tell GitHub what operating system environment to provision for our job execution.

            * `ubuntu-latest` 
                (Recommended: fastest, cheapest, contains pre-installed tools like Java, Node, Docker, Maven).
            * `windows-latest` 
                (For .NET framework apps or Windows-specific execution).
            * `macos-latest` 
                (Mainly for iOS/macOS mobile compilation).

        Block D: The Executable Steps (`steps:`)

            Steps are the granular tasks that run sequentially *inside* a single job runner. A step can only do *one of two things*: execute a raw shell command or invoke a pre-built plugin (Action).

            1. Calling Pre-Built Plugins (`uses:`)

                Instead of writing complex scripts to download Java, log into AWS, or clone code, you reference community actions using the format `owner/repo-name@version`.

                - name: Clone Git Repository Code
                    uses: actions/checkout@v4 # Essential first step for almost all pipelines

                - name: Setup Java Environment
                    uses: actions/setup-java@v4
                    with:
                        java-version: '17'
                        distribution: 'temurin'

            *(The `with:` block passes configuration arguments directly into that specific plugin).*

            2. Running Raw Terminal Commands (`run:`)

                This is where you execute standard CLI commands exactly as you would on your local terminal machine.
                
                - name: Run Single Line Command
                    run: npm install

                - name: Run Multi-line Script Block
                    run: |
                        echo "Starting compilation phase..."
                        mvn clean package -DskipTests
                        echo "Compilation completed successfully!"

            3. Dynamic Injection: Contexts and Secrets

                Hardcoding passwords, database strings, or ecosystem configurations straight into your YAML files is a massive security risk. GitHub Actions provides global wrappers to safely inject dynamic data at runtime.

                Repository Secrets (`${{ secrets.BOD_VAL }}`)

                    Any sensitive credential stored under your GitHub Repository Settings is automatically encrypted. The pipeline reads them securely using the `secrets` context wrapper:
        
                    - name: Authenticate to Container Registry
                        run: docker login -u ${{ secrets.DOCKER_USERNAME }} -p ${{ secrets.DOCKER_PASSWORD }}
                
                Context Variations (`${{ github.XYZ }}`)

                    GitHub injects a live contextual payload metadata object into every pipeline execution. We can leverage this to make decisions dynamically:

                        * `${{ github.sha }}`: 
                            The exact commit hash that triggered the build (excellent for tagging unique Docker images).

                        * `${{ github.ref }}`: 
                            The branch reference path (e.g., `refs/heads/main`).

            4. Troubleshooting a Newly Created File

                When we write your first pipeline file, save it into your local repository strictly within this exact path:  `.github/workflows/main-pipeline.yml`

                If the execution fails immediately upon pushing:

                1. Check our spacing: Ensure every nested property uses exactly 2 or 4 spaces consistently relative to its parent node wrapper.
                2. Validate the syntax: Ensure keywords like `jobs`, `steps`, `uses`, and `run` are lowercase.
                3. Inspect the logs: 
                    Go to the repository on GitHub 
                    -> Click the "Actions" tab 
                    -> Click the running/failed workflow 
                    -> Click the job name to watch lines of shell execution compile in real-time. 
                        Every error code or failed command will print directly there.    