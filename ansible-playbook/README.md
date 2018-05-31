# General
Current folder contains ansible playbook code, that executes all tasks described in the README.md in the root folder of the repo. The logic of the playbook is following:
- The main playbook performs setup of the hypervisor and everything related to the installing software and configuring the network on the remote server and upload to the remote server secondary playbook
- The secondary playbook preforms configuring and setup of the VM's

Such logic was chosen due to an inability of Ansible to configure remote hosts that placed behind the NAT.

## Main playbook structure
Main Ansible playbook consists of the following components:
- General task—task.yml
- Var file—group_vars/main.yml
- Roles
- Inventory file—hosts

### Roles
Following roles are implemented:
- the cloud-init role that contains all info for provisioning new VM's
- create_net role contains actions for VM network preparation and creation inside the hypervisor
- create_vm role contains actions for VM's creation
- haproxy_install role contains actions for HAproxy install and setup
- software_install role contains actions for install all necessary software to create and setup hypervisor inside the remote server
- vm_provisioning role contains actions to upload nested Ansible playbook to the remote server and execute it

### Example of execution
Example of execution:
```
ansible-playbook -i hosts task.yml
```

## Secondary playbook structure
Secondary Ansible playbook consists of the following components:
- General task—site.yml
- Var file—group_vars/main.yml
- Roles
- Inventory file—hosts

### Roles
Following roles are implemented:
- apache2 role contains actions for installation and configuration of apache2
- iptables role contains actions for securing host and close everything except 22 and 80 ports. Also, this role add the repo for the security updates and upgrade all packages if the updates available
- postgresql role contains actions for installing and configuring PostgreSQL and creates tables and user for Wordpress CMS
- wordpress role contains actions for installing and configuring Wordpress

### Example of execution
Example of execution:
```
ansible-playbook -i hosts site.yml
```

## Limitations and preparations
I'm assuming that on the command center (laptop, PC or any other computer from which playbook will be launched) will be configured ssh_config in the following way:
```
Host srv1.example.com
  HostName 1.1.1.1
  User some_user
  IdentityFile ~/.ssh/rsa_id
 ```
