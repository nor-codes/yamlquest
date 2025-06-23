#!/bin/bash
# install.sh - download and install YAMLQuest binary

set -e

URL="https://github.com/nor-codes/yamlquest/releases/download/v1.0.0-beta.2/YAMLQuest_1.0.0-beta.2_linux_amd64.tar.gz"
TMP_ARCHIVE="/tmp/yamlquest.tar.gz"

echo "Downloading YAMLQuest from $URL ..."
curl -L -o "$TMP_ARCHIVE" "$URL"

echo "Extracting to ~/.local/bin ..."
mkdir -p ~/.local/bin
tar -xzf "$TMP_ARCHIVE" -C ~/.local/bin

chmod +x ~/.local/bin/yquest

# Add ~/.local/bin to PATH if not already present
if ! grep -q 'export PATH="$HOME/.local/bin:$PATH"' ~/.bashrc; then
  echo 'export PATH="$HOME/.local/bin:$PATH"' >> ~/.bashrc
  echo "Added ~/.local/bin to PATH in ~/.bashrc"
fi

rm "$TMP_ARCHIVE"

echo "Installation complete! Please reload your shell or run 'source ~/.bashrc'"
