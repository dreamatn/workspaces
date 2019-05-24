package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZLPAY {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTSCASH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    double WS_VALUE_BAL = 0;
    double WS_TMP_AMT = 0;
    char WS_CONDIFLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBRMST IBRMST = new IBRMST();
    IBRSCASH IBRSCASH = new IBRSCASH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCLPAY IBCLPAY;
    public void MP(SCCGWA SCCGWA, IBCLPAY IBCLPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCLPAY = IBCLPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZLPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CHECK_AC_BAL();
        if (pgmRtn) return;
        B030_CHECK_PAY_AC_BAL();
        if (pgmRtn) return;
        B040_CREDIT_L_AC();
        if (pgmRtn) return;
        B050_DEBIT_PAY_AC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCLPAY.PAY_AMT);
        if (IBCLPAY.PAY_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AMT, IBCLPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        IBRSCASH.VOSTRO_AC = IBCLPAY.AC_NO;
        T000_READ_IBTSCASH();
        if (pgmRtn) return;
        if (WS_CONDIFLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCLPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, IBRSCASH.AC_NO, IBRMST);
        T000_READ_IBTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
        if (IBRMST.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCLPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_PAY_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBRMST.OD_PAY_AC;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCLPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCQINF.OUTPUT_DATA.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFFECTIVE_DATE, IBCLPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCQINF.OUTPUT_DATA.VALUE_BAL < IBCLPAY.PAY_AMT) {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCLPAY.PAY_AMT);
    }
    public void B040_CREDIT_L_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.AC = IBRMST.KEY.AC_NO;
        IBCPOSTA.CCY = IBCLPAY.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = IBCLPAY.PAY_AMT;
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBRMST.OD_PAY_AC;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.TX_MMO = "A805";
        B060_GET_AC_INFO();
        if (pgmRtn) return;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B050_DEBIT_PAY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.AC = IBRMST.OD_PAY_AC;
        IBCPOSTA.CCY = IBCLPAY.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = IBCLPAY.PAY_AMT;
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBRMST.KEY.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.TX_MMO = "A805";
        B060_GET_AC_INFO();
        if (pgmRtn) return;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B060_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBCPOSTA.OTH_AC_NO;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        IBCPOSTA.OTH_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        IBCPOSTA.OTH_CNM = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "VOSTRO_AC = :IBCLPAY.AC_NO";
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDIFLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDIFLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTSCASH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
