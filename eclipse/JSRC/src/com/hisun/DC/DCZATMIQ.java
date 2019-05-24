package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZATMIQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC390";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 8;
    String K_HIS_REMARK = "CHECK CARD BALANCES ON THE ATM";
    short K_PAGE_ROW = 25;
    String WS_CARD_NO = " ";
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICCUST CICCUST = new CICCUST();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICQACRL CICQACRL = new CICQACRL();
    DCCF390 DCCF390 = new DCCF390();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS3090 DCCS3090;
    public void MP(SCCGWA SCCGWA, DCCS3090 DCCS3090) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS3090 = DCCS3090;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZATMIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF390);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_PROC();
        if (pgmRtn) return;
        B020_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS3090.CARD_NO);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCS3090.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCPCDCK);
        CEP.TRC(SCCGWA, "PASSWORD");
        CEP.TRC(SCCGWA, DCCS3090.PSW);
        CEP.TRC(SCCGWA, DCCS3090.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCS3090.TRK3_DAT);
        if ((DCCS3090.TRK2_DAT.trim().length() > 0) 
            && (DCCS3090.TRK3_DAT.trim().length() > 0)) {
            CEP.TRC(SCCGWA, "PSW+TRK");
            DCCPCDCK.FUNC_CODE = 'B';
            DCCPCDCK.CARD_NO = DCCS3090.CARD_NO;
            DCCPCDCK.CARD_PSW = DCCS3090.PSW;
            DCCPCDCK.TRK2_DAT = DCCS3090.TRK2_DAT;
            DCCPCDCK.TRK3_DAT = DCCS3090.TRK3_DAT;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "PSW");
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_NO = DCCS3090.CARD_NO;
            DCCPCDCK.CARD_PSW = DCCS3090.PSW;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (DCCUCINF.ADSC_TYPE == 'C') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DCCS3090.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_CARD_NO = DCCS3090.CARD_NO;
        }
        CEP.TRC(SCCGWA, WS_CARD_NO);
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_CARD_NO;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
    }
    public void B020_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCF390.CARD_NO = DCCS3090.CARD_NO;
        DCCF390.AVL_BAL = DDCIQBAL.DATA.AVL_BAL;
        DCCF390.BALANCE = DDCIQBAL.DATA.CURR_BAL;
        DCCF390.ISSU_BR = DCCUCINF.ISSU_BR;
        DCCF390.TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        CEP.TRC(SCCGWA, DCCF390.AVL_BAL);
        CEP.TRC(SCCGWA, DCCF390.BALANCE);
        CEP.TRC(SCCGWA, DCCF390.TEL_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF390;
        SCCFMT.DATA_LEN = 76;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICCUST.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
