package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQINF {
    DBParm IBTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String K_MMO_IB = "IB";
    String TAB_IBTMST = "IBTMST  ";
    char K_AC_STATUS_CODE = '1';
    char K_AC_NORMAL = 'N';
    char K_AC_BLOCK = 'B';
    char K_AC_CLOSED = 'C';
    char K_AC_LHOLD = 'L';
    char WS_FLAG = ' ';
    short WS_CNT = 0;
    double WS_DR_BAL = 0;
    double WS_CR_BAL = 0;
    short WS_I = 0;
    IBZQINF_WS_AC_STATUS WS_AC_STATUS = new IBZQINF_WS_AC_STATUS();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICACCU CICACCU = new CICACCU();
    AICPQBAL AICPQBAL = new AICPQBAL();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    IBCQINF IBCQINF;
    public void MP(SCCGWA SCCGWA, IBCQINF IBCQINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQINF = IBCQINF;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBCQINF.RC.RC_MMO = K_MMO_IB;
        IBCQINF.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT();
        if (pgmRtn) return;
        B20_GET_AC_INFO();
        if (pgmRtn) return;
        B30_GET_CUST_INFO();
        if (pgmRtn) return;
        B70_PROC_OUTPUT();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
        if (IBCQINF.INPUT_DATA.AC_NO.trim().length() == 0 
            && (IBCQINF.INPUT_DATA.NOSTRO_CD.trim().length() == 0 
            || IBCQINF.INPUT_DATA.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBCQINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_GET_AC_INFO() throws IOException,SQLException,Exception {
        if (IBCQINF.INPUT_DATA.NOSTRO_CD.trim().length() > 0 
            && IBCQINF.INPUT_DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, IBRMST);
            IBRMST.NOSTRO_CODE = IBCQINF.INPUT_DATA.NOSTRO_CD;
            IBRMST.CCY = IBCQINF.INPUT_DATA.CCY;
            T00_STARTBR_IBTMST_FIRST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
            IBCQINF.INPUT_DATA.AC_NO = IBRMST.KEY.AC_NO;
        }
        if (IBCQINF.INPUT_DATA.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, IBRMST);
            IBRMST.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
            T00_READ_IBTMST();
            if (pgmRtn) return;
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBRMST.NOSTRO_CODE;
            IBCQINF.INPUT_DATA.CCY = IBRMST.CCY;
        }
    }
    public void B30_GET_CUST_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBRMST.KEY.AC_NO;
        S00_LINK_CIZACCU();
        if (pgmRtn) return;
    }
    public void B70_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBCQINF.OUTPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
        IBCQINF.OUTPUT_DATA.AC_ENG_NAME = CICACCU.DATA.AC_ENM;
        IBCQINF.OUTPUT_DATA.AC_CHN_NAME = CICACCU.DATA.AC_CNM;
        IBCQINF.INPUT_DATA.AC_NO = IBRMST.KEY.AC_NO;
        IBCQINF.INPUT_DATA.NOSTRO_CD = IBRMST.NOSTRO_CODE;
        IBCQINF.INPUT_DATA.CCY = IBRMST.CCY;
        IBCQINF.OUTPUT_DATA.PROD_CD = IBRMST.PROD_CD;
        IBCQINF.OUTPUT_DATA.OIC_NO = IBRMST.OIC_NO;
        IBCQINF.OUTPUT_DATA.RESP_CD = IBRMST.RESP_CD;
        IBCQINF.OUTPUT_DATA.SUB_DP = IBRMST.SUB_DP;
        IBCQINF.OUTPUT_DATA.VALUE_BAL = IBRMST.VALUE_BAL;
        IBCQINF.OUTPUT_DATA.HLD_AMT = IBRMST.HLD_AMT;
        IBCQINF.OUTPUT_DATA.FST_BVD = IBRMST.FST_BVD;
        IBCQINF.OUTPUT_DATA.AC_ATTR = IBRMST.AC_ATTR;
        IBCQINF.OUTPUT_DATA.FUND_ATTR = IBRMST.FUND_ATTR;
        IBCQINF.OUTPUT_DATA.AC_NATR = IBRMST.AC_NATR;
        IBCQINF.OUTPUT_DATA.VALUE_TAX = IBRMST.VALUE_TAX;
        IBCQINF.OUTPUT_DATA.CORRAC_BK = IBRMST.CORRAC_BK;
        IBCQINF.OUTPUT_DATA.CORRAC_NO = IBRMST.CORRAC_NO;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.VALUE_BAL);
        CEP.TRC(SCCGWA, IBRMST.HLD_AMT);
        IBS.CPY2CLS(SCCGWA, IBRMST.AC_STS_WORD, WS_AC_STATUS);
        if (WS_AC_STATUS.WS_STS_NORMA == K_AC_STATUS_CODE) {
            IBCQINF.OUTPUT_DATA.AC_STS = K_AC_NORMAL;
        }
        if (WS_AC_STATUS.WS_STS_BLOCK == K_AC_STATUS_CODE) {
            IBCQINF.OUTPUT_DATA.AC_STS = K_AC_BLOCK;
        }
        if (WS_AC_STATUS.WS_STS_CLOSD == K_AC_STATUS_CODE) {
            IBCQINF.OUTPUT_DATA.AC_STS = K_AC_CLOSED;
        }
        if (WS_AC_STATUS.WS_STS_LHOLD == K_AC_STATUS_CODE) {
            IBCQINF.OUTPUT_DATA.AC_STS = K_AC_LHOLD;
        }
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_NORMA);
        CEP.TRC(SCCGWA, IBRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_BLOCK);
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_CLOSD);
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_LHOLD);
        IBCQINF.OUTPUT_DATA.EFF_DATE = IBRMST.EFF_DATE;
        IBCQINF.OUTPUT_DATA.PRV_FLAG = IBRMST.PRV_FLAG;
        IBCQINF.OUTPUT_DATA.RATE_FLAG = IBRMST.RATE_FLAG;
        IBCQINF.OUTPUT_DATA.RATE_MTH = IBRMST.RATE_MTH;
        IBCQINF.OUTPUT_DATA.CALR_STD = IBRMST.CALR_STD;
        IBCQINF.OUTPUT_DATA.INTS_CYC = IBRMST.INTS_CYC;
        IBCQINF.OUTPUT_DATA.INTS_DT_MM = IBRMST.INTS_DT_MM;
        IBCQINF.OUTPUT_DATA.INTS_DT_DD = IBRMST.INTS_DT_DD;
        IBCQINF.OUTPUT_DATA.RATE = IBRMST.RATE;
        IBCQINF.OUTPUT_DATA.RATE_TYPE = IBRMST.RATE_TYPE;
        IBCQINF.OUTPUT_DATA.RATE_TERM = IBRMST.RATE_TERM;
        IBCQINF.OUTPUT_DATA.RATE_PCT = IBRMST.RATE_PCT;
        IBCQINF.OUTPUT_DATA.RATE_SPREAD = IBRMST.RATE_SPREAD;
        IBCQINF.OUTPUT_DATA.L_INTS_DATE = IBRMST.L_INTS_DT;
        if (IBRMST.BUD_INT1 != 0) {
            IBCQINF.OUTPUT_DATA.BUD_INT = IBRMST.BUD_INT1 * -1;
        } else {
            IBCQINF.OUTPUT_DATA.BUD_INT = IBRMST.BUD_INT;
        }
        IBCQINF.OUTPUT_DATA.OD_PAY_AC = IBRMST.OD_PAY_AC;
        IBCQINF.OUTPUT_DATA.OD_FLAG = IBRMST.OD_FLAG;
        IBCQINF.OUTPUT_DATA.OD_RATE_FLAG = IBRMST.OD_RATE_FLAG;
        IBCQINF.OUTPUT_DATA.OD_RATE = IBRMST.OD_RATE;
        IBCQINF.OUTPUT_DATA.OD_INTS_CYC = IBRMST.OD_INTS_CYC;
        IBCQINF.OUTPUT_DATA.OD_L_INTS_DT = IBRMST.OD_L_INTS_DT;
        IBCQINF.OUTPUT_DATA.OD_BUD_INT = IBRMST.OD_BUD_INT;
        IBCQINF.OUTPUT_DATA.OD_L_BUD_INT = IBRMST.OD_L_BUD_INT;
        IBCQINF.OUTPUT_DATA.OPEN_DATE = IBRMST.OPEN_DATE;
        IBCQINF.OUTPUT_DATA.OPEN_BR = IBRMST.OPEN_BR;
        IBCQINF.OUTPUT_DATA.OPEN_TLR = IBRMST.OPEN_TLR;
        IBCQINF.OUTPUT_DATA.OWNER_BK = IBRMST.OWNER_BK;
        IBCQINF.OUTPUT_DATA.AUTH_TLR = IBRMST.AUTH_TLR;
        IBCQINF.OUTPUT_DATA.CLOSE_DATE = IBRMST.CLOS_DATE;
        IBCQINF.OUTPUT_DATA.CLOSE_BR = IBRMST.CLOS_BR;
        IBCQINF.OUTPUT_DATA.CLOSE_TLR = IBRMST.CLOS_TLR;
        IBCQINF.OUTPUT_DATA.LAST_FI_DATE = IBRMST.LAST_FI_DATE;
        IBCQINF.OUTPUT_DATA.LAST_MAINT_DATE = IBRMST.UPD_DATE;
        IBCQINF.OUTPUT_DATA.L_MAINT_TLR = IBRMST.UPD_TLR;
        IBCQINF.OUTPUT_DATA.POST_ACT_CTR = IBRMST.POST_CTR;
        IBCQINF.OUTPUT_DATA.SWR_BR = IBRMST.SWR_BR;
        IBCQINF.OUTPUT_DATA.L_VALUE_BAL = IBRMST.L_VALUE_BAL;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.OPEN_DATE);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CLOSE_DATE);
    }
    public void T00_STARTBR_IBTMST_FIRST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND CCY = :IBRMST.CCY";
        IBTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCQINF.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCQINF.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S00_LINK_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            IBCQINF.RC.RC_MMO = CICACCU.RC.RC_MMO;
            IBCQINF.RC.RC_CODE = CICACCU.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (IBCQINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "IBCQINF = ");
            CEP.TRC(SCCGWA, IBCQINF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
