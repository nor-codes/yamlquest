#!/bin/bash
# uninstall.sh - simple uninstall script for YAMLQuest

# Remove the binary from ~/.local/bin
rm -f ~/.local/bin/yquest

# Remove the PATH export line from ~/.bashrc
sed -i '/export PATH="$HOME\/.local\/bin:$PATH"/d' ~/.bashrc

echo "Uninstalled YAMLQuest binary and removed PATH modification from ~/.bashrc."
echo "Please reload your shell or run 'source ~/.bashrc' to apply changes."
