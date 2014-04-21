package com.hs.whocan.component.account.security.dao.hbm;

import com.hs.whocan.component.account.security.dao.PhoneAuthCode;
import com.hs.whocan.component.account.security.dao.PhoneAuthCodeDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PhoneAuthCodeRepository implements PhoneAuthCodeDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public PhoneAuthCode findPhoneAuthCode(String phoneNo) {
        final String hql = "from PhoneAuthCodeEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo",phoneNo);
        PhoneAuthCodeEntity entity = (PhoneAuthCodeEntity) query.uniqueResult();
        return entity!=null?entity.getPhoneAuthCode():null;
    }

    @Override
    public void createPhoneAuthCode(PhoneAuthCode phoneAuthCode) {
        sessionFactory.getCurrentSession().save(new PhoneAuthCodeEntity(phoneAuthCode));
    }

    @Override
    public PhoneAuthCode findPhoneAuthCode(String phoneNo, int authCode) {
        final String hql = "from PhoneAuthCodeEntity e where e.phoneNo = :phoneNo and e.authCode = :authCode and e.authCode != 0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo",phoneNo);
        query.setInteger("authCode", authCode);
        PhoneAuthCodeEntity entity = (PhoneAuthCodeEntity) query.uniqueResult();
        return entity!=null?entity.getPhoneAuthCode():null;
    }
}
