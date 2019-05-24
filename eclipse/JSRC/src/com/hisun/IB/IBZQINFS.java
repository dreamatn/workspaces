package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQINFS {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMST_RD;
    DBParm IBTTMAIN_RD;
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String WS_TABLE_NAME = " ";
    char K_AC_STATUS_CODE = '1';
    char K_AC_NORMAL = 'N';
    char K_AC_BLOCK = 'B';
    char K_AC_CLOSED = 'C';
    char K_AC_LHOLD = 'L';
    IBZQINFS_WS_AC_STATUS WS_AC_STATUS = new IBZQINFS_WS_AC_STATUS();
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICACCU CICACCU = new CICACCU();
    IBRTMST IBRTMST = new IBRTMST();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQINFS IBCQINFS;
    public void MP(SCCGWA SCCGWA, IBCQINFS IBCQINFS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQINFS = IBCQINFS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQINFS return!");
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
        B020_GET_AC_INFO();
        if (pgmRtn) return;
        B030_PROC_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCQINFS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCQINFS.CCY);
        if ((IBCQINFS.PRIM_AC_NO.trim().length() == 0) 
            && (IBCQINFS.NOSTR_CD.trim().length() == 0 
            || IBCQINFS.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCQINFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCQINFS.SEQ_NO);
        if (IBCQINFS.SEQ_NO == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT, IBCQINFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBS.init(SCCGWA, IBRTMST);
        if (IBCQINFS.PRIM_AC_NO.trim().length() > 0) {
            IBRTMAIN.KEY.AC_NO = IBCQINFS.PRIM_AC_NO;
            T000_READ_IBTMAIN1();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_TMAIN, IBCQINFS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBCQINFS.NOSTR_CD = IBRTMAIN.NOSTRO_CODE;
            IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
            IBRTMST.SEQ_NO = IBCQINFS.SEQ_NO;
        } else {
            IBRTMAIN.NOSTRO_CODE = IBCQINFS.NOSTR_CD;
            IBRTMAIN.CCY = IBCQINFS.CCY;
            T000_READ_IBTMAIN2();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_TMAIN, IBCQINFS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
            IBRTMST.SEQ_NO = IBCQINFS.SEQ_NO;
        }
        T000_READ_IBTTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_TMST, IBCQINFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBRTMAIN.KEY.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B030_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        IBCQINFS.CI_NO = CICACCU.DATA.CI_NO;
        IBCQINFS.CUSTNME = CICACCU.DATA.AC_CNM;
        IBCQINFS.NOSTR_CD = IBRTMAIN.NOSTRO_CODE;
        IBCQINFS.AC_NO = IBRTMST.KEY.AC_NO;
        IBCQINFS.PRIM_AC_NO = IBRTMST.PRIM_AC_NO;
        IBCQINFS.SEQ_NO = IBRTMST.SEQ_NO;
        IBCQINFS.POST_CTR = IBRTMST.POST_CTR;
        IBCQINFS.STS = IBRTMST.STS;
        IBCQINFS.STSW = IBRTMST.STSW;
        IBCQINFS.CCY = IBRTMST.CCY;
        IBCQINFS.OIC_NO = IBRTMST.OIC_NO;
        IBCQINFS.RESP_CD = IBRTMST.RESP_CD;
        IBCQINFS.SUB_DP = IBRTMST.SUB_DP;
        IBCQINFS.OWNER_BK = IBRTMST.OWNER_BK;
        IBCQINFS.OPEN_BR = IBRTMST.OPEN_BR;
        IBCQINFS.OPEN_DATE = IBRTMST.OPEN_DATE;
        IBCQINFS.OPEN_TLR = IBRTMST.OPEN_TLR;
        IBCQINFS.PROD_CD = IBRTMST.PROD_CD;
        IBCQINFS.CORR_BK_CD = IBRTMST.CORR_BK_CD;
        IBCQINFS.CORR_AC_NO = IBRTMST.CORR_AC_NO;
        IBCQINFS.CORR_DEPR_NO = IBRTMST.CORR_DEPR_NO;
        IBCQINFS.INTS_AC_TYPE = IBRTMST.INTS_AC_TYPE;
        IBCQINFS.INTS_AC_NO = IBRTMST.INTS_AC_NO;
        IBCQINFS.FUND_ATTR = IBRTMST.FUND_ATTR;
        IBCQINFS.CALR_STD = IBRTMST.CALR_STD;
        IBCQINFS.RATE = IBRTMST.RATE;
        IBCQINFS.ADV_RATE = IBRTMST.ADV_RATE;
        IBCQINFS.OVD_RATE = IBRTMST.OVD_RATE;
        IBCQINFS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCQINFS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCQINFS.PVAL_DATE = IBRTMST.PVAL_DATE;
        IBCQINFS.RATE_MTH = IBRTMST.RATE_MTH;
        IBCQINFS.INTS_CYC = IBRTMST.INTS_CYC;
        IBCQINFS.OPEN_BAL = IBRTMST.OPEN_BAL;
        IBCQINFS.CURR_BAL = IBRTMST.CURR_BAL;
        IBCQINFS.LBAL = IBRTMST.LBAL;
        IBCQINFS.LAST_FI_DATE = IBRTMST.LAST_FI_DATE;
        IBCQINFS.EXP_INT = IBRTMST.EXP_INT;
        IBCQINFS.DRW_INT = IBRTMST.DRW_INT;
        IBCQINFS.BUD_INT = IBRTMST.BUD_INT;
        IBCQINFS.LBUD_DATE = IBRTMST.LBUD_DATE;
        IBCQINFS.LSET_DATE = IBRTMST.LSET_DATE;
        IBCQINFS.CLOS_DATE = IBRTMST.CLOS_DATE;
        IBCQINFS.LAST_BR = IBRTMST.LAST_BR;
        IBCQINFS.CRT_DATE = IBRTMST.CRT_DATE;
        IBCQINFS.CRT_TLR = IBRTMST.CRT_TLR;
        IBCQINFS.AUTH_TLR = IBRTMST.AUTH_TLR;
        IBCQINFS.UPD_DATE = IBRTMST.UPD_DATE;
        IBCQINFS.UPD_TIME = IBRTMST.UPD_TIME;
        IBCQINFS.UPD_TLR = IBRTMST.UPD_TLR;
        IBCQINFS.AC_NATR = IBRTMST.AC_NATR;
        IBCQINFS.VALUE_TAX = IBRTMST.VALUE_TAX;
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCQINFS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = :IBRTMST.SEQ_NO";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMAIN1() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMAIN2() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
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
