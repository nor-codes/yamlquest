Project Overview: YAML Quest CLI
YAML Quest CLI is a command-line application designed to execute HTTP requests defined in YAML files. It aims to provide a simple yet powerful way to run and manage API calls directly from YAML configuration, similar to tools like Postman but optimized for the command line.

This project is a learning experience and is being built primarily to upskill and polish my Golang skills. While I’m not an expert, I’m using this as a way to enhance my proficiency in Golang, experiment with different tools, and explore new technologies, .

I believe that personal projects like this are essential for growth, and I welcome contributions from anyone interested in learning alongside me!
Contact:

For feedback or suggestions, feel free to reach out to me at:
devnamednor@gmail.com

## Requirements

- Linux-based system (other platforms coming soon)
- Java 21 or higher installed
- `sudo` privileges

## 📥 Installation Instructions

### Step 1: Download the Installation Script

Download the `install.sh` script from the link below:

- [Download install.sh](https://github.com/nor-codes/yamlquest/releases/download/v1.0.0-beta.1/install.sh)

### Step 2: Run the Installation Script

Once downloaded, navigate to the directory where the script is located, then run:

```bash
sudo bash ./install.sh
```

### Step 3: Verify Installation

```bash
yq-cli --help
```

## 🗑️ Uninstallation Instructions

Download the `uninstall.sh` script from the link below:

- [Download uninstall.sh](https://github.com/nor-codes/yamlquest/releases/download/v1.0.0-beta.1/uninstall.sh)

Once downloaded run:
```bash
sudo bash ./uninstall.sh
```

Future Plans:
Response Validation & Testing: Add the ability to validate responses from APIs and include test scripts to automate the validation process.
Integration with CI/CD Pipelines: Tailored for usage in infrastructure and API testing, enabling integration into CI/CD workflows.
Web & Desktop Versions: Transition from CLI to full-fledged web or desktop versions in the future.
Environmental Variables Support: In the next beta release, we plan to add support for environmental variables. This will allow users to define and use environment variables in their YAML files, making it easier to manage dynamic API requests. Additionally, users will be able to view environment variables directly from the CLI.


# YAML Quest CLI - Usage Instructions

## Defining API Requests in YAML

To use **YAML Quest CLI**, you need to define your API requests in a YAML file. Below is an example of how to structure the requests:

```yaml
requests:
  - name: "Create Post"
    method: "POST"
    url: "https://jsonplaceholder.typicode.com/posts"
    headers:
      Authorization: "Bearer sampleToken"
    contentType: "application/json"
    body: |
      {
        "title": "foo",
        "body": "bar",
        "userId": 1
      }
  - name: "Get Comments"
    method: "GET"
    url: "https://jsonplaceholder.typicode.com/comments"
    contentType: "application/json"
```

- **name**:  
  A descriptive name for the request (e.g., "Create Post").

- **method**:  
  The HTTP method to use for the request (e.g., GET, POST).

- **url**:  
  The URL endpoint for the request.

- **headers**:  
  Any HTTP headers to include with the request (e.g., Authorization).

- **contentType**:  
  The content type of the request body (e.g., `application/json`).

- **body**:  
  The body of the request, typically used for POST or PUT methods. It can be defined as a JSON string.

Once you have defined your requests in the YAML file, you can run the requests using the following command:
```bash
yq-cli execute --file <path-to-file>
```


🎉 Thank you for using YAMLQuest CLI!
