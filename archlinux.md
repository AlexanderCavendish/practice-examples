#Archlinux安装

### 之前旧电脑直装过Arch跑过一阵子,后来换了新电脑一直用的win10,最近需要个linux系统,所以在虚拟机装,但是发现我流程忘得差不多了,这次做个笔记记录一下供以后参考.sdX表示我当时自己的分区,这个具体看自己的.

1.vmworkstation设置启动模式uefi
  
2.判断是否支持uefi

	ls /sys/firmware/efi/efivars

3.查看盘

	fdisk -l

4.分区

	fdisk /dev/sda

    输入g,代表gpt

	输入n

	输入 +1g (/mnt/efi的空间)

	之后分区操作同此步```

5.分区预览

	输入p
		例如
			sda1 1g(/mnt/efi)
			sda2 99g(/mnt)
			sda3 400g(/mnt/home)


6.写入分区
 
	输入w

7.格式化分区

	mkfs.ext4 /dev/sda1
	以ext4方式格式化磁盘/dev/sda的/dev/sda1分区
	mkfs.vfat -F32 /dev/sda2			
	以vfat方式创建efi

8.挂载分区

	mount /dev/sda2 /mnt
	mkdir /mnt/efi
	mount /dev/sda1 /mnt/efi
	mkdir /mnt/home
	mount /dev/sda3 /mnt/home

9.改源

	vi /etc/pacman.d/mirrorlist
	中国的留下来,也可以自己添加几个

10.安装arch

	pacstrap /mnt base base-devel

11.生成分区表
	
	genfstab -U /mnt >> /mnt/etc/fstab

12.进入系统
	
	arch-chroot /mnt

13.安装

	noto-fonts-cjk google字体
	timedatectl set-ntp true

14.设置时区

	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

15.本地化设置

	nano,vi都行,看自己习惯
	nano /etc/locale.gen
	F6搜索 zh_CN.UTF-8
	删除前面字符 #
	保存退出
	输入locale-gen 生成 locale。

16.设置主机名

	echo AMC > /etc/hostname
	接着向 /etc/hosts 文件添加 hosts 条目。
	#<ip-address>	<hostname.domain.org>		<hostname>
	127.0.0.1		localhost.localdomain		localhost
	::1				localhost.localdomain		localhost
	127.0.1.1		AMC.localdomain				AMC

17.设置ROOT密码
 
	passwd

18.安装引导程序

	BIOS 系统：
	pacman -S grub os-prober
	grub-install --target=i386-pc /dev/sdX
	grub-mkconfig -o /boot/grub/grub.cfg

	UEFI 系统：
	pacman -S dosfstools grub efibootmgr
	grub-install --target=x86_64-efi --efi-directory=EFI 分区挂载点 --bootloader-id=GRUB
	grub-mkconfig -o /boot/grub/grub.cfg

19.添加用户

	useradd -m -g users -G wheel -s /bin/bash amc
	passwd amc
	安装 sudo
	pacman -S sudo
	打开 /etc/sudoers 文件，找到 root ALL=(ALL) ALL 
	下面添加 amc ALL=(ALL) ALL 即可


20.VMware(真机装需要别的驱动,自行百度)
	
	pacman -S mesa lsb-release xf86-input-vmmouse xf86-video-vmware open-vm-tools gtkmm
	open-vm-tools开机自启
	systemctl enable vmtoolsd.service
	systemctl enable vmware-vmblock-fuse.service

21.net相关
	
	安装NetworkManager,nm-connection-editor,network-manager-applet
	systemctl disable netctl 自带关闭
	systemctl enable NetworkManager

22.ok

	exit
	umount /mnt/efi
	umount /mnt
	reboot

之后就是美化,gnome,kde啥的.

[ArchLinux-WIKI](https://wiki.archlinux.org/index.php/Arch_Linux)
