package com.remme.springboot.domain.posts;

import lombok.Getter;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// jpa entity 클래스들이 baseTimeEntity를 상속할 경우 필드를 컬럼으로 인식하도록 한다.
@MappedSuperclass
// baseTimeEntity클래스에 auditing 기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntitiy {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
