package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5800 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSOPAC DDCSOPAC = new DDCSOPAC();
    SCCGWA SCCGWA;
    DDB5800_AWA_5800 DDB5800_AWA_5800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5800 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5800_AWA_5800>");
        DDB5800_AWA_5800 = (DDB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        DDB5800_AWA_5800.CCY = "" + 156;
        JIBS_tmp_int = DDB5800_AWA_5800.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDB5800_AWA_5800.CCY = "0" + DDB5800_AWA_5800.CCY;
        DDB5800_AWA_5800.CCY_TYPE = '1';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "OPEN-DD-AC");
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.CI_NO);
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.PROD_CD);
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.AC_TYP);
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.DRAW_MTH);
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.BV_TYPE);
        if (DDB5800_AWA_5800.CI_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CI_NO_M_INPUT;
            if (DDB5800_AWA_5800.CI_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5800_AWA_5800.CI_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5800_AWA_5800.PROD_CD.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_NEW_PROD_M_INPUT;
            WS_FLD_NO = DDB5800_AWA_5800.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if ((DDB5800_AWA_5800.BV_TYPE == '1' 
            || DDB5800_AWA_5800.BV_TYPE == 'B') 
            && DDB5800_AWA_5800.AC_TYP == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_TYP_INPUT;
            WS_FLD_NO = DDB5800_AWA_5800.AC_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.PSBK_FLG);
        CEP.TRC(SCCGWA, DDB5800_AWA_5800.AC_TYP);
        if (DDB5800_AWA_5800.PSBK_FLG == '1' 
            && (DDB5800_AWA_5800.AC_TYP != 'A' 
            && DDB5800_AWA_5800.AC_TYP != 'B')) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
            WS_FLD_NO = DDB5800_AWA_5800.AC_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5800_AWA_5800.PSBK_FLG == '1' 
            && DDB5800_AWA_5800.DRAW_MTH == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_DRAW_MTH_M_INPUT;
            WS_FLD_NO = DDB5800_AWA_5800.DRAW_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5800_AWA_5800.DRAW_MTH != ' ' 
            && DDB5800_AWA_5800.DRAW_MTH != '1' 
            && DDB5800_AWA_5800.DRAW_MTH != '2' 
            && DDB5800_AWA_5800.DRAW_MTH != '3' 
            && DDB5800_AWA_5800.DRAW_MTH != '4' 
            && DDB5800_AWA_5800.DRAW_MTH != '5') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_DRAW_MTH_ERR;
            WS_FLD_NO = DDB5800_AWA_5800.DRAW_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5800_AWA_5800.CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5800_AWA_5800.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5800_AWA_5800.CCY_TYPE == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            WS_FLD_NO = DDB5800_AWA_5800.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSOPAC);
        DDCSOPAC.CI_NO = DDB5800_AWA_5800.CI_NO;
        DDCSOPAC.PROD_CODE = DDB5800_AWA_5800.PROD_CD;
        DDCSOPAC.AC_TYP = DDB5800_AWA_5800.AC_TYP;
        DDCSOPAC.CCY = DDB5800_AWA_5800.CCY;
        DDCSOPAC.CCY_TYPE = DDB5800_AWA_5800.CCY_TYPE;
        DDCSOPAC.PSBK_FLG = DDB5800_AWA_5800.PSBK_FLG;
        DDCSOPAC.CARD_FLG = DDB5800_AWA_5800.CARD_FLG;
        DDCSOPAC.PSBK_NO = DDB5800_AWA_5800.PSBK_NO;
        DDCSOPAC.CMAD_TYP = DDB5800_AWA_5800.CMAD_TYP;
        DDCSOPAC.CARD_NO = DDB5800_AWA_5800.CARD_NO;
        DDCSOPAC.CARD_TYP = DDB5800_AWA_5800.CARD_TYP;
        DDCSOPAC.COLACFLG = DDB5800_AWA_5800.COLACFLG;
        DDCSOPAC.CARD_PRD_CODE = DDB5800_AWA_5800.CARD_PRD;
        DDCSOPAC.CARD_BIN = DDB5800_AWA_5800.CARD_BIN;
        DDCSOPAC.CARD_FIRST_SEQ = DDB5800_AWA_5800.RGN_CODE;
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_FIRST_SEQ);
        DDCSOPAC.CARD_MTH = DDB5800_AWA_5800.CARD_MTH;
        DDCSOPAC.CARD_SEQ = DDB5800_AWA_5800.CARD_SEQ;
        DDCSOPAC.DRAW_MTH = DDB5800_AWA_5800.DRAW_MTH;
        DDCSOPAC.CARD_MODE = DDB5800_AWA_5800.CARD_TPY;
        DDCSOPAC.CARD_PRN_CNM = DDB5800_AWA_5800.CARD_PRN;
        DDCSOPAC.CARD_DUE = DDB5800_AWA_5800.CARD_DUE;
        DDCSOPAC.DRAW_MTH = DDB5800_AWA_5800.DRAW_MTH;
        DDCSOPAC.PSWD = DDB5800_AWA_5800.PSWD;
        DDCSOPAC.REMARK = DDB5800_AWA_5800.REMARK;
        DDCSOPAC.CUS_MGR = DDB5800_AWA_5800.CUS_MGR;
        DDCSOPAC.REG_CENT = DDB5800_AWA_5800.REG_CENT;
        DDCSOPAC.SUB_BIZ = DDB5800_AWA_5800.SUB_BIZ;
        DDCSOPAC.AC_TYPE = DDB5800_AWA_5800.AC_TYPE;
        DDCSOPAC.AGID_TYP = DDB5800_AWA_5800.AGID_TYP;
        DDCSOPAC.AGID_NO = DDB5800_AWA_5800.AGID_NO;
        DDCSOPAC.AG_NM = DDB5800_AWA_5800.AG_NM;
        DDCSOPAC.POST_AC = DDB5800_AWA_5800.POST_AC;
        DDCSOPAC.DB_FREE = DDB5800_AWA_5800.DB_FREE;
        DDCSOPAC.YW_TYP = DDB5800_AWA_5800.YW_TYP;
        DDCSOPAC.CARD_CLS = DDB5800_AWA_5800.CARD_CLS;
        DDCSOPAC.BV_CD_NO = DDB5800_AWA_5800.BV_CD_NO;
        DDCSOPAC.INT_TYP = DDB5800_AWA_5800.INT_TYP;
        DDCSOPAC.DR_FLG = DDB5800_AWA_5800.DR_FLG;
        DDCSOPAC.BV_TYPE = DDB5800_AWA_5800.BV_TYPE;
        DDCSOPAC.TRT_CTLW = DDB5800_AWA_5800.TRT_CTLW;
        DDCSOPAC.LMT_CCY = DDB5800_AWA_5800.LMT_CCY;
        DDCSOPAC.LMT_BAL = DDB5800_AWA_5800.LMT_BAL;
        DDCSOPAC.AGID_CTY = DDB5800_AWA_5800.AGID_CTY;
        DDCSOPAC.AGID_CNO = DDB5800_AWA_5800.AGID_CNO;
        DDCSOPAC.AGID_CNM = DDB5800_AWA_5800.AGID_CNM;
        S000_CALL_DDZSOPAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-OPEN-AC", DDCSOPAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
