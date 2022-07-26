##明铭社区

##资料

https://spring.io/guides
https://spring.io/guides/gs/serving-web-content/
https://docs.github.com/cn/developers/apps/building-github-apps/creating-a-github-app   

##工具

##脚本

```sql
create table GITHUB_USER
(
    ID           INTEGER auto_increment,
    ACCOUNT_ID   CHARACTER VARYING(100),
    NAME         CHARACTER VARYING(50),
    TOKEN        CHARACTER(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
```