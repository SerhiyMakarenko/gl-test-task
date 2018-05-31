# General
The aim of this Test Task is to create Ansible playbook which will setup hypervisor, create two virtual machines, set up them in the proper way, place them behind the load balancer and create Jenkins pipeline, that will be run Ansible playbook by specific schedule.

This repo contains the following code:
- Ansible playbook
- Jenkins scripts for creating the pipeline

Ansible playbook can be found in the ansible-playbook folder.
Jenkins scripts can be found in the Jenkins-pipeline folder.

## List of sub-tasks
List of subtasks that should be implemented with the code in this repo:

1. Install on the host hypervisor KVM and all necessary packages (qemu-kvm, virsh, etc.)
2. Prepare the image for the virtualization. This is can be image downloaded from cloud-images.ubuntu.com, or cloud image for any other distribution (CentOS, Debian, etc.), or created manually. The image should be able to work with cloud-init out of the box
3. Initialize with this image two VM's in KVM (Ansible virt modules in the preview state, so shell commands should be used instead)
4. Initialize network in both instances with cloud-init (with mounted during boot iso image) and install all necessary packages for Ansible
5. Install in instances PostgreSQL, Apache and any CMS that able to work with PostgreSQL—WordPress, Drupal, Bolt, etc
6. Configure CMS for PostgreSQL DB and Apache web server (this actions should be performed with Ansible templates or modules, similar to lineinfile)
7. Place HAproxy or any other balancer in front of the VM's and setup balancing of HTTP traffic between two instances
8. Update security packages inside instances and setup firewall (with iptables)
9. Pull the code to the GitHub/GitLab for the review
10. Install and setup Jenkins, create the pipeline which will be pulling out the ansible code from GitHub/GitLab and run automatically by schedule

## Limitations and additional info
The following limitation and requirements should be taken into account in solving this task:

- If the task can be done with the bash script it's okay to use it running from the Ansible
- The URL's, passwords and IP addresses should be stored in the var files
- Instead of using var files acceptable to place some variables in the inventory file for the particular role
- All tasks should be rerunnable. This means that second and following runs should do not broke the environment
- Use Ansible roles
- Passwords should be stored in the encrypted file
- It's okay to use Nginx instead of Apache
- Do not use ansible-galaxy
