#!/usr/bin/env groovy

def ansibleVmProvisionSyntaxValidate() {
	git branch: "${BRANCH}", credentialsId: '347a97e7-6224-45f3-84ac-4c9c4709121c', url: "${ANSIBLE_URL}"
	stage('Playbook syntax check') {
		sh 'chmod 400 .ssh/id_rsa'
		sh "ansible-playbook --syntax-check -i hosts task.yml "
	}
}

def ansibleVmProvisionValidate() {
	git branch: "${BRANCH}", credentialsId: '347a97e7-6224-45f3-84ac-4c9c4709121c', url: "${ANSIBLE_URL}"
	stage('Playbook validate') {
		sh 'chmod 400 .ssh/id_rsa'
		sh "ansible-playbook --check -i hosts task.yml"
	}
}

def ansibleVmProvisionApply() {
	git branch: "${BRANCH}", credentialsId: '347a97e7-6224-45f3-84ac-4c9c4709121c', url: "${ANSIBLE_URL}"
	stage('Playbook apply') {
		sh 'chmod 400 .ssh/id_rsa'
		sh "ansible-playbook -i hosts task.yml"
	}
}

return this