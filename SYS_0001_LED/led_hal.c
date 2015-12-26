
#define LOG_TAG "LedHal"
/* 1. ʵ��һ����ΪHMI��hw_module_t�ṹ�� */

/* 2. ʵ��һ��open����, ������led_device_t�ṹ�� */

/* 3. ʵ��led_device_t�ṹ�� */

/* �ο� hardware\libhardware\modules\vibrator\vibrator.c
 */
#include <hardware/hardware.h>
#include <cutils/log.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <hardware/led_hal.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <utils/Log.h>

static int fd;

/** Close this device */
static int led_close(struct hw_device_t* device)
{
	close(fd);
	return 0;
}

static int led_open(struct led_device_t* dev)
{
	fd = open("/dev/leds", O_RDWR);
	ALOGI("led_open : %d", fd);
	if (fd >= 0)
		return 0;
	else
		return -1;
}

static int led_ctrl(struct led_device_t* dev, int which, int status)
{
	int ret = ioctl(fd, status, which);
	ALOGI("led_ctrl : %d, %d, %d", which, status, ret);
	return ret;
}

static struct led_device_t led_dev = {
	.common = {
		.tag   = HARDWARE_DEVICE_TAG,
		.close = led_close,
	},
	.led_open  = led_open,
	.led_ctrl  = led_ctrl,
};

static int led_device_open(const struct hw_module_t* module, const char* id,
        struct hw_device_t** device)
{
	*device = &led_dev;
	return 0;
}


static struct hw_module_methods_t led_module_methods = {
    .open = led_device_open,
};

struct hw_module_t HAL_MODULE_INFO_SYM = {
	.tag = HARDWARE_MODULE_TAG,
    .id = "led",
    .methods = &led_module_methods,
};


