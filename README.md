# slacker

[![Build Status](https://travis-ci.org/ccguyka/slacker.svg?branch=master)](https://travis-ci.org/ccguyka/slacker)
[![Coverage Status](https://coveralls.io/repos/github/ccguyka/slacker/badge.svg?branch=master)](https://coveralls.io/github/ccguyka/slacker?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/58df730726a5bb0052202fd1/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58df730726a5bb0052202fd1)

slacker is a tool for simple slack API access via webhooks.
It is a command line application that currently only supports simple messages.

## Usage

```bash
java -jar slacker.jar
 -a,--author <arg>       Author of the message
 -c,--color <arg>        Color of the message, default: 'warning'
 -h,--help               print this message
 -hk,--hook <arg>        Slack to hook
 -m,--message <arg>      Message to be sent
 -t,--title <arg>        Title of the message
 -tl,--titlelink <arg>   The link where title should refer to
```

## Message

Slack [formatting](https://api.slack.com/docs/message-formatting) is supported for the message text (but not for the title).

### Examples

- Basic text formatting

  <code>&grave;&grave;&grave;pre&grave;&grave;&grave;</code>, <code>&grave;code&grave;</code>, <code>&lowbar;italic&lowbar;</code>, <code>&ast;bold&ast;</code>, <code>&tilde;strike&tilde;</code>

- Links

  `<http://www.foo.com|text>`
