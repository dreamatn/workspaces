package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3050 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    String WS_CARD_NO = " ";
    int WS_CARD_SEQ = 0;
    String WS_AC = " ";
    String WS_AC_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    int WS_CARD_BR = 0;
    String WS_TRN_CCY = " ";
    double WS_TRN_AMT = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    String WS_SMR = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRRPT AIRRPT = new AIRRPT();
    AICHGLRP AICNGLRP = new AICHGLRP();
    AICHGLRP AICOGLRP = new AICHGLRP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRGLRP AICRGLRP = new AICRGLRP();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    CICCUST CICCUST = new CICCUST();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CMCF001 CMCF001 = new CMCF001();
    CICQACAC CICQACAC = new CICQACAC();
    SCRCWA SCRCWA = new SCRCWA();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3050 CMCS3050;
    public void MP(SCCGWA SCCGWA, CMCS3050 CMCS3050) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3050 = CMCS3050;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3050 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF001);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B300_TRN_PROC();
        if (pgmRtn) return;
        B700_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS3050.BUSI_KND == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_KND_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.TXN_CCY.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CCY_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.TXN_AMT == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AMT_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.ID_FLG == 'Y' 
            && (CMCS3050.ID_TYP.trim().length() == 0 
            || CMCS3050.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.NM_FLG == 'Y' 
            && CMCS3050.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.TEL_FLG == 'Y' 
            && CMCS3050.TEL_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TEL_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.TXN_RMK.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_RMK_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3050.TXN_RMK == null) CMCS3050.TXN_RMK = "";
        JIBS_tmp_int = CMCS3050.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3050.TXN_RMK += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3050.TXN_RMK.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_MMO_NOT_EXIST);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
    }
    public void B200_CI_CHECK() throws IOException,SQLException,Exception {
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS3050.CARD_NO;
        R000_CALL_CICCUST();
        if (pgmRtn) return;
        CMCF001.AC_NM = CICCUST.O_DATA.O_CI_NM;
        CMCF001.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        CMCF001.ID_NO = CICCUST.O_DATA.O_ID_NO;
        CMCF001.TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        if (CMCS3050.TEL_FLG == 'Y' 
            && !CMCS3050.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TELNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.NM_FLG == 'Y' 
            && !CMCS3050.CI_NM.equalsIgnoreCase(CMCF001.AC_NM)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CINM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.ID_FLG == 'Y' 
            && !CMCS3050.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDTYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3050.ID_FLG == 'Y' 
            && !CMCS3050.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B300_TRN_PROC() throws IOException,SQLException,Exception {
        if (CMCS3050.BUSI_KND == '1' 
            || (CMCS3050.BUSI_KND == '0' 
            && CMCS3050.TX_TYP == '1')) {
            R000_CALL_AIZUUPIA_DR();
            if (pgmRtn) return;
            R000_CALL_BPCUABOX();
            if (pgmRtn) return;
        }
        if (CMCS3050.BUSI_KND == '0' 
            || (CMCS3050.BUSI_KND == '1' 
            && CMCS3050.TX_TYP == '1')) {
            R000_CALL_AIZUUPIA_CR();
            if (pgmRtn) return;
            R000_CALL_BPCUSBOX();
            if (pgmRtn) return;
        }
    }
    public void B500_CARD_INF() throws IOException,SQLException,Exception {
        DCCUCINF.CARD_NO = CMCS3050.CARD_NO;
        R000_CALL_DCCUCINF();
        if (pgmRtn) return;
        CMCF001.CARD_BR = DCCUCINF.ISSU_BR;
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS3050.CARD_NO;
        R000_CALL_CICQACAC();
        if (pgmRtn) return;
        CMCF001.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B600_INQ_BAL() throws IOException,SQLException,Exception {
        DDCIQBAL.DATA.AC = CMCS3050.CARD_NO;
        R000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF001.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCF001.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
    }
    public void B700_OUTPUT() throws IOException,SQLException,Exception {
        CMCF001.TRN_AMT = CMCS3050.TXN_AMT;
        CMCF001.CARD_NO = CMCS3050.CARD_NO;
        CMCF001.CARD_SEQ = CMCS3050.CARD_SEQ;
        CMCF001.TRN_CCY = CMCS3050.TXN_CCY;
        CMCF001.SMR = CMCS3050.SMR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM300";
        SCCFMT.DATA_PTR = CMCF001;
        SCCFMT.DATA_LEN = 599;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void R000_CALL_AIZUUPIA_DR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = CMCS3050.SLT_AC;
        AICUUPIA.DATA.CCY = CMCS3050.TXN_CCY;
        AICUUPIA.DATA.THEIR_CCY = CMCS3050.TXN_CCY;
        AICUUPIA.DATA.AMT = CMCS3050.TXN_AMT;
        AICUUPIA.DATA.THEIR_AMT = CMCS3050.TXN_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.NARR_CD = CMCS3050.TXN_RMK;
        AICUUPIA.DATA.THEIR_AC = CMCS3050.CARD_NO;
        AICUUPIA.DATA.ORI_AC = CMCS3050.CARD_NO;
        AICUUPIA.DATA.PAY_MAN = CMCS3050.CI_NM;
        S_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void R000_CALL_AIZUUPIA_CR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.AC_NO = CMCS3050.SLT_AC;
        AICUUPIA.DATA.CCY = CMCS3050.TXN_CCY;
        AICUUPIA.DATA.THEIR_CCY = CMCS3050.TXN_CCY;
        AICUUPIA.DATA.AMT = CMCS3050.TXN_AMT;
        AICUUPIA.DATA.THEIR_AMT = CMCS3050.TXN_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.NARR_CD = CMCS3050.TXN_RMK;
        AICUUPIA.DATA.THEIR_AC = CMCS3050.CARD_NO;
        AICUUPIA.DATA.ORI_AC = CMCS3050.CARD_NO;
        AICUUPIA.DATA.PAY_MAN = CMCS3050.CI_NM;
        S_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void R000_CALL_BPCUABOX() throws IOException,SQLException,Exception {
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.CCY = "156";
        BPCUSBOX.AMT = CMCS3050.TXN_AMT;
        BPCUSBOX.OPP_AC = CMCS3050.SLT_AC;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.RMK = CMCS3050.SMR;
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void R000_CALL_BPCUSBOX() throws IOException,SQLException,Exception {
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.CCY = "156";
        BPCUABOX.AMT = CMCS3050.TXN_AMT;
        BPCUABOX.OPP_AC = CMCS3050.SLT_AC;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.RMK = CMCS3050.SMR;
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void R000_CALL_DCCUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void R000_CALL_CICQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA, true);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        CEP.TRC(SCCGWA, BPCFFCON.RC.RC_CODE);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF);
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
