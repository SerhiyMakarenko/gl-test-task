# General
Current folder contains Jenkins pipeline code, that executes Ansible playbook automatically by specific schedule pulling out the Ansible code from the remote repo. The logic of the pipeline is following:
- pipeline checkout code from remote repo
- performs Ansible playbook syntax check
- performs Ansible playbook validation
- performs Ansible playbook execution

## Pipeline variables
Mentioned pipeline required following variables for successful execution:
- ANSIBLE_URL— the variable which defines git repo URL with Ansible playbook code
- BUILD_REPO_URL— the variable which defines git repo URL with Jenkins provisioning scripts
- BRANCH— the variable which defines git repo branch

## Pipeline configuration
We need to create the new pipeline in Jenkins admin panel and set the following values:
- Set checkbox in front of "This project is parameterized" in "General" section
- Add three block for string parameters in "General" section that will correspond to the mentioned above variables
- Set checkbox in front of "Build periodically" in "Build Triggers" section
- Set the desired schedule
- In "Pipeline" section choose 
   - Definition: Pipeline script from SCM
   - SCM: Git
   - Point Jenkins to the current repo
   - Script Path: /jenkins-pipeline/ansible-provisioning.groovy

## Limitations and preparations
According to the settings of the build environment hosts file should have the similar to the following content:
```
[hypervisor]
srv1.example.com ansible_user=some_user ansible_ssh_private_key_file=.ssh/id_rsa ansible_ssh_extra_args='-o StrictHostKeyChecking=no'
```
