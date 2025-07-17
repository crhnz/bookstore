source "virtualbox-iso" "ubuntu" {
  iso_url            = "https://releases.ubuntu.com/22.04/ubuntu-22.04.4-live-server-amd64.iso"
  iso_checksum       = "auto"
  ssh_username       = "vagrant"
  ssh_password       = "vagrant"
  shutdown_command   = "shutdown -P now"
  communicator       = "ssh"
  guest_os_type      = "Ubuntu_64"
  vm_name            = "springboot-compose"
  output_directory   = "output-ova"
}

build {
  sources = ["source.virtualbox-iso.ubuntu"]

  provisioner "shell" {
    script = "scripts/install-docker.sh"
  }

  provisioner "file" {
    source      = "springboot-app.tar"
    destination = "/home/vagrant/springboot-app.tar"
  }

  provisioner "file" {
    source      = "docker-compose.yml"
    destination = "/home/vagrant/docker-compose.yml"
  }

  provisioner "shell" {
    script = "scripts/load-image.sh"
  }

  provisioner "shell" {
    script = "scripts/start-compose.sh"
  }
}
