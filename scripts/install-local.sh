#!/bin/bash
# install.sh - a simple install script for YAMLQuest

# Extract binary to ~/.local/bin
mkdir -p ~/.local/bin
tar -xzf YAMLQuest_1.0.0-beta.2_linux_amd64.tar.gz -C ~/.local/bin

# Make sure executable
chmod +x ~/.local/bin/yquest

# Add ~/.local/bin to PATH if not already
if ! grep -q 'export PATH="$HOME/.local/bin:$PATH"' ~/.bashrc; then
  echo 'export PATH="$HOME/.local/bin:$PATH"' >> ~/.bashrc
  echo "Added ~/.local/bin to PATH in ~/.bashrc"
fi

echo "Installation complete! Reload your shell or run 'source ~/.bashrc'"
