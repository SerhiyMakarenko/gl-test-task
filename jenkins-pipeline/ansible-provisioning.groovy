#!/usr/bin/env groovy

node {

	// Ansible playbooks repo URL
	ANSIBLE_URL = "${ANSIBLE_URL}"

	// Jenkins build script repo URL
	BUILD_REPO_URL = "${BUILD_REPO_URL}"

	// clone $BUILD_REPO_URL to dedicated directory ./buildscripts/
	dir('buildscripts') {
		git branch: 'master', credentialsId: '347a97e7-6224-45f3-84ac-4c9c4709121c', url: "${BUILD_REPO_URL}"
	}

	def provision = load 'buildscripts/ansible-playbook.groovy'

	provision.ansibleVmProvisionSyntaxValidate()
	provision.ansibleVmProvisionValidate()
	provision.ansibleVmProvisionApply()
}