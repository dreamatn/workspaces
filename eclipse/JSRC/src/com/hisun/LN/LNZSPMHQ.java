package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPMHQ {
    int JIBS_tmp_int;
    DBParm LNTSETL_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTICTL_RD;
    DBParm LNTLOAN_RD;
    brParm LNTPAIP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_ID = "LNZ07";
    LNZSPMHQ_WS_MSGID WS_MSGID = new LNZSPMHQ_WS_MSGID();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCRFWDH LNCRFWDH = new LNCRFWDH();
    LNCSEXT LNCSEXT = new LNCSEXT();
    LNCHUEXT LNCHUEXT = new LNCHUEXT();
    BPRNHIST BPRNHIST = new BPRNHIST();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRSETL LNRSETL = new LNRSETL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    LNCSPMHQ LNCSPMHQ;
    public void MP(SCCGWA SCCGWA, LNCSPMHQ LNCSPMHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPMHQ = LNCSPMHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPMHQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICIQ.RC.RC_MMO = "LN";
        LNCICIQ.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_RECORD();
        if (pgmRtn) return;
        B200_OUT_RECORD();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
    }
    public void B100_GET_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        LNRSETL.KEY.SETTLE_TYPE = '1';
        T00_READ_LNTSETL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCSPMHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        T00_READ_LNTAPRD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCSPMHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSPMHQ.COMM_DATA.CTA_NO);
        IBS.init(SCCGWA, LNCICIQ);
        CEP.TRC(SCCGWA, LNCSPMHQ.COMM_DATA.CTA_NO);
        LNCICIQ.DATA.CONT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        LNCICIQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCICIQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCICIQ.DATA.SUB_CONT_NO = "0" + LNCICIQ.DATA.SUB_CONT_NO;
        LNCICIQ.FUNC = 'M';
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CONT_NO);
        S000_CALL_LNZICIQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T00_READ_LNTICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCSPMHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLOSED, LNCSPMHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRAPRD.PAY_MTH == '4') {
            IBS.init(SCCGWA, LNRPAIP);
            LNRPAIP.KEY.CONTRACT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
            LNRPAIP.KEY.SUB_CTA_NO = 0;
            T000_STARTBR_LNTPAIP();
            if (pgmRtn) return;
            T000_READNEXT_LNTPAIP();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCSPMHQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTPAIP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRLOAN);
        CEP.TRC(SCCGWA, LNCSPMHQ.COMM_DATA.CTA_NO);
        LNRLOAN.KEY.CONTRACT_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
    }
    public void B200_OUT_RECORD() throws IOException,SQLException,Exception {
        LNCSPMHQ.COMM_DATA.CCY_TYP = LNRSETL.CCY_TYP;
        LNCSPMHQ.COMM_DATA.C_RP_M = LNRAPRD.PAY_MTH;
        LNCSPMHQ.COMM_DATA.B_RP_M = LNRAPRD.BPAY_MTH;
        LNCSPMHQ.COMM_DATA.IN_MTH = LNRAPRD.INST_MTH;
        LNCSPMHQ.COMM_DATA.P_PERD = LNRAPRD.PAYP_PERD;
        LNCSPMHQ.COMM_DATA.P_UNIT = LNRAPRD.PAYP_PERD_UNIT;
        LNCSPMHQ.COMM_DATA.C_TYP = LNRAPRD.PAY_DD_TYPE;
        LNCSPMHQ.COMM_DATA.C_DAY = LNRAPRD.PAY_DAY;
        LNCSPMHQ.COMM_DATA.C_PERD = LNRAPRD.PAY_DD_LTERM;
        if (LNRAPRD.BPAYP_PERD == ' ') LNCSPMHQ.COMM_DATA.PRAL_PERD = 0;
        else LNCSPMHQ.COMM_DATA.PRAL_PERD = Short.parseShort(""+LNRAPRD.BPAYP_PERD);
        LNCSPMHQ.COMM_DATA.P_GRA = LNRAPRD.P_GRACE_TERM;
        if (LNRAPRD.BCAL_PERD == ' ') LNCSPMHQ.COMM_DATA.INTER_PERD = 0;
        else LNCSPMHQ.COMM_DATA.INTER_PERD = Short.parseShort(""+LNRAPRD.BCAL_PERD);
        LNCSPMHQ.COMM_DATA.I_PERD = LNRAPRD.CAL_PERD;
        LNCSPMHQ.COMM_DATA.I_UNIT = LNRAPRD.CAL_PERD_UNIT;
        LNCSPMHQ.COMM_DATA.CAL_PERD = LNRAPRD.OCAL_PERD;
        LNCSPMHQ.COMM_DATA.CAL_UNIT = LNRAPRD.OCAL_PERD_UNIT;
        LNCSPMHQ.COMM_DATA.CI_NO = LNCICIQ.DATA.CI_NO;
        LNCSPMHQ.COMM_DATA.CI_CNM = LNCICIQ.DATA.CI_CNAME;
        LNCSPMHQ.COMM_DATA.PRD_TYP = LNCICIQ.DATA.PROD_CD;
        LNCSPMHQ.COMM_DATA.CCY = LNCICIQ.DATA.CCY;
        LNCSPMHQ.COMM_DATA.PRIN_AMT = LNCICIQ.DATA.AMT;
        LNCSPMHQ.COMM_DATA.OS_BAL = LNCICIQ.DATA.BAL;
        LNCSPMHQ.COMM_DATA.START_DT = LNCICIQ.DATA.VAL_DT;
        LNCSPMHQ.COMM_DATA.MATU_DT = LNCICIQ.DATA.DUE_DT;
        CEP.TRC(SCCGWA, LNRLOAN.FST_CAL_DT);
        if (LNRICTL.IC_CAL_TERM == 1) {
            LNCSPMHQ.COMM_DATA.INT_ED = LNRLOAN.FST_CAL_DT;
        } else {
            LNCSPMHQ.COMM_DATA.INT_ED = LNRICTL.IC_CAL_DUE_DT;
        }
        LNCSPMHQ.COMM_DATA.TOT_TERM = LNRPAIP.KEY.PHS_NO;
        if (LNRAPRD.SPEC_LN_FLG == '1') {
            LNCSPMHQ.COMM_DATA.B_PERD = LNRPAIP.PHS_TOT_TERM;
        } else {
            LNCSPMHQ.COMM_DATA.B_PERD = 0;
        }
    }
    public void T00_READ_LNTSETL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        LNTSETL_RD.order = "SEQ_NO DESC";
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPAIP_BR.rp.order = "PHS_NO DESC";
        IBS.STARTBR(SCCGWA, LNRPAIP, LNTPAIP_BR);
    }
    public void T000_READNEXT_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSPMHQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPMHQ=");
            CEP.TRC(SCCGWA, LNCSPMHQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
