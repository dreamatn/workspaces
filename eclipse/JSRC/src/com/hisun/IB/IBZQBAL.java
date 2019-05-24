package com.hisun.IB;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQBAL {
    String JIBS_tmp_str[] = new String[10];
    brParm IBTBALF_BR = new brParm();
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String WS_TABLE_NAME = " ";
    String WS_TS_REC = "                                                                                                                                                                                                                                                                                                                                                                                              ";
    short WS_TS_LEN = 0;
    int WS_COUNT = 0;
    int WS_TS_CNT = 0;
    IBZQBAL_WS_OUTPUT_BALF WS_OUTPUT_BALF = new IBZQBAL_WS_OUTPUT_BALF();
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    String WS_IN_AC_NO = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINF IBCQINF = new IBCQINF();
    IBRBALF IBRBALF = new IBRBALF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQBAL IBCQBAL;
    public void MP(SCCGWA SCCGWA, IBCQBAL IBCQBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQBAL = IBCQBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQBAL return!");
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
        B020_INT_BALF_INQ();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQBAL.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCQBAL.CCY);
        CEP.TRC(SCCGWA, IBCQBAL.AC_NO);
        if (IBCQBAL.NOSTR_CD.trim().length() == 0 
            && IBCQBAL.CCY.trim().length() == 0 
            && IBCQBAL.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        B010_01_CHECK_AC_EXIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, IBCQBAL.POST_CTR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706660500 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW);
        }
        CEP.TRC(SCCGWA, IBCQBAL.STR_DATE);
        CEP.TRC(SCCGWA, IBCQBAL.END_DATE);
        if (IBCQBAL.STR_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || IBCQBAL.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IPT_DATE_GE_ACDATE);
        }
        if (IBCQBAL.STR_DATE > IBCQBAL.END_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
        }
    }
    public void B010_01_CHECK_AC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCQBAL.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCQBAL.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCQBAL.NOSTR_CD;
            IBCQINF.INPUT_DATA.CCY = IBCQBAL.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        WS_STR_DATE = IBCQBAL.STR_DATE;
        WS_END_DATE = IBCQBAL.END_DATE;
        WS_IN_AC_NO = IBCQINF.INPUT_DATA.AC_NO;
    }
    public void B020_INT_BALF_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 354;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_COUNT = 0;
        B020_01_BALF_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B020_01_BALF_INQ_PROC() throws IOException,SQLException,Exception {
        IBRBALF.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        CEP.TRC(SCCGWA, IBRBALF.KEY.AC_NO);
        T000_STARTBR_IBTBALF();
        if (pgmRtn) return;
        T000_READNEXT_IBTBALF();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_OUTPUT_PROC();
            if (pgmRtn) return;
            WS_COUNT = WS_COUNT + 1;
            T000_READNEXT_IBTBALF();
            if (pgmRtn) return;
        }
        T000_ENDBR_IBTBALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
    }
    public void R000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_BALF);
        WS_OUTPUT_BALF.WS_AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        WS_OUTPUT_BALF.WS_NOSTR_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
        WS_OUTPUT_BALF.WS_CCY = IBCQINF.INPUT_DATA.CCY;
        WS_OUTPUT_BALF.WS_CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        WS_OUTPUT_BALF.WS_BAL_DATE = IBRBALF.KEY.BAL_DATE;
        WS_OUTPUT_BALF.WS_BAL = IBRBALF.BAL;
        WS_OUTPUT_BALF.WS_AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
        WS_OUTPUT_BALF.WS_UPD_DATE = IBRBALF.UPD_DATE;
        WS_OUTPUT_BALF.WS_PROD_CD = IBCQINF.OUTPUT_DATA.PROD_CD;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_BALF);
        SCCMPAG.DATA_LEN = 354;
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_STARTBR_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_BR.rp = new DBParm();
        IBTBALF_BR.rp.TableName = "IBTBALF";
        IBTBALF_BR.rp.where = "BAL_DATE >= :WS_STR_DATE "
            + "AND BAL_DATE <= :WS_END_DATE "
            + "AND AC_NO = :WS_IN_AC_NO";
        IBTBALF_BR.rp.order = "BAL_DATE";
        IBS.STARTBR(SCCGWA, IBRBALF, this, IBTBALF_BR);
    }
    public void T000_READNEXT_IBTBALF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRBALF, this, IBTBALF_BR);
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
    public void T000_ENDBR_IBTBALF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTBALF_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
