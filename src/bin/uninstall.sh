#!/bin/bash

# Ensure we have the correct permissions
if [ "$(id -u)" -ne 0 ]; then
  echo "This script requires sudo to uninstall. Exiting."
  exit 1
fi

# Define install paths
INSTALL_DIR="/usr/local/bin/yq-cli"
INSTALL_SHARE_DIR="/usr/local/share/yq-cli"

echo "Removing yq-cli from $INSTALL_DIR and $INSTALL_SHARE_DIR..."

# Remove executable
if [ -f "$INSTALL_DIR" ]; then
  rm -f "$INSTALL_DIR"
  echo "Removed: $INSTALL_DIR"
else
  echo "Executable not found in $INSTALL_DIR"
fi

# Remove JAR and share directory
if [ -d "$INSTALL_SHARE_DIR" ]; then
  rm -rf "$INSTALL_SHARE_DIR"
  echo "Removed: $INSTALL_SHARE_DIR"
else
  echo "Share directory not found at $INSTALL_SHARE_DIR"
fi

echo "yq-cli uninstallation complete."
