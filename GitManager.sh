#!/bin/bash

# Enable debugging
 set -x

# Directory to scan
TARGET_DIR="/d/Sampark_Automation/Sampark"

# Folder names to delete (space-separated list)
FOLDERS_TO_DELETE=("logs" "screenshots" "test-output" "test-results")

# Navigate to the target directory
cd "$TARGET_DIR" || { echo "Failed to navigate to $TARGET_DIR"; exit 1; }

# Delete specific folders if they exist
for folder in "${FOLDERS_TO_DELETE[@]}"; do
    if [ -d "$folder" ]; then
        echo "Deleting folder: $folder"
        rm -rf "$folder"
    fi
done

# Navigate to project directory
PROJECT_DIR="/d/Sampark_Automation/Sampark"
cd "$PROJECT_DIR" || { echo "Failed to navigate to $PROJECT_DIR"; exit 1; }

# Run Maven clean install
echo " "
echo "Cleaning and installing the maven repository"
mvn clean install

# Wait for 30 seconds
sleep 20

# Check which files are being staged
echo " "
echo "Checking which files have to be staged"
git status
sleep 10

# Git operations with wait intervals
echo " "
echo "Adding files to staged area"
git add .
sleep 10

# Get the current user's name
USER_NAME=$(git config user.name)

# Get staged file names (only staged files)
CHANGED_FILES=$(git diff --cached --name-only | head -n 10 | tr '\n' ' ')

# Check if there are changes to commit
if [ -z "$CHANGED_FILES" ]; then
    echo "No changes to commit. Exiting..."
    exit 0
fi

# Generate dynamic commit message
COMMIT_MESSAGE="Push by: $USER_NAME | Updated files: $CHANGED_FILES"

# Commit changes with generated message
echo " "
echo "Committing the changes"
git commit -m "$COMMIT_MESSAGE"
sleep 10

# Push changes to the main branch
echo " "
echo "Pushing all changes to remote repository"
git push origin master
sleep 10
