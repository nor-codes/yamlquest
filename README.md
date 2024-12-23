Project Overview: YAML Quest CLI
YAML Quest CLI is a command-line application designed to execute HTTP requests defined in YAML files. It aims to provide a simple yet powerful way to run and manage API calls directly from YAML configuration, similar to tools like Postman but optimized for the command line.

Key Features:

YAML-Based Request Definitions: Users define API requests (method, URL, headers, body, etc.) in YAML files.
Request Execution: The application parses the YAML file, extracts the requests, and sends them using HTTP methods like GET, POST, etc.
CLI Command Interface: Execute requests from the terminal using commands like yq request -f request1.yaml.
Future Plans:

Response Validation & Testing: Add the ability to validate responses from APIs and include test scripts to automate the validation process.
Integration with CI/CD Pipelines: Tailored for usage in infrastructure and API testing, enabling integration into CI/CD workflows.
Web & Desktop Versions: Transition from CLI to full-fledged web or desktop versions in the future.
The project is built using Java to improve my skills in the language, with future plans to explore other technologies like Go.
