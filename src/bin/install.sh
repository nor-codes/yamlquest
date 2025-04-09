
#!/bin/bash

# Ensure we have the correct permissions to install
if [ "$(id -u)" -ne 0 ]; then
  echo "This script requires sudo to install. Exiting."
  exit 1
fi

# Define installation locations
INSTALL_DIR="/usr/local/bin"
INSTALL_SHARE_DIR="/usr/local/share/yq-cli"

# Define the JAR file and download URL
JAR_FILE="yamlquest-cli-1.0-SNAPSHOT-all.jar"
DOWNLOAD_URL="https://drive.usercontent.google.com/download?id=1lONDR9ceWD9gmYDuq7ORGUiZRf9gRHz_&export=download&authuser=0&confirm=t&uuid=a0944429-9450-4c6a-8d40-6259c1c8de4e&at=APcmpoz4BStemat3mHEIUfNZsuUj:1743713702806"

# Step 1: Download the JAR file
echo "Downloading yq-cli JAR file..."
curl -o /tmp/$JAR_FILE $DOWNLOAD_URL

# Step 2: Create the executable script
echo "Creating executable script..."

cat << EOF > /tmp/yq-cli
#!/bin/bash
java -jar $INSTALL_SHARE_DIR/$JAR_FILE "\$@"
EOF

# Step 3: Move the JAR and script to installation directory
echo "Installing files..."
mkdir -p $INSTALL_SHARE_DIR
mv /tmp/$JAR_FILE $INSTALL_SHARE_DIR/
chmod +x /tmp/yq-cli
mv /tmp/yq-cli $INSTALL_DIR/

# Step 4: Verify installation
if command -v yq-cli &>/dev/null; then
    echo "yq-cli successfully installed. You can now use it by typing 'yq-cli'."
else
    echo "Installation failed. Please try again."
    exit 1
fi
