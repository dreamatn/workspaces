package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPGDIN;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZSFGAM {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD542";
    String K_HIS_COPYBOOK_NAME = "DDCSFGAM";
    String K_HIS_TX_CD = "0115342";
    String K_HIS_REMARKS = "FROGIN LIMITED AMOUNT AMT CONTROL";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_PCALL_TYP = "PDD19";
    String K_PCALL_CD = "001";
    String WS_MSGID = " ";
    short WS_IDX = 0;
    char WS_MODIFY_FLG = ' ';
    DDZSFGAM_WS_OLD_DATA WS_OLD_DATA = new DDZSFGAM_WS_OLD_DATA();
    DDZSFGAM_WS_NEW_DATA WS_NEW_DATA = new DDZSFGAM_WS_NEW_DATA();
    DDZSFGAM_WS_OFGAM WS_OFGAM = new DDZSFGAM_WS_OFGAM();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDVDPAM3 DDVDPAM3 = new DDVDPAM3();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    DDCOATAC DDCOATAC = new DDCOATAC();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSMFLG DDCSMFLG;
    DDCSFGAM DDCSFGAM;
    public void MP(SCCGWA SCCGWA, DDCSFGAM DDCSFGAM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSFGAM = DDCSFGAM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSFGAM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSFGAM.FUNC);
        CEP.TRC(SCCGWA, DDCSFGAM.BNK);
        CEP.TRC(SCCGWA, DDCSFGAM.CCY);
        CEP.TRC(SCCGWA, DDCSFGAM.EXR_TYP);
        CEP.TRC(SCCGWA, DDCSFGAM.FLG);
        CEP.TRC(SCCGWA, DDCSFGAM.FRG_LAMT);
        CEP.TRC(SCCGWA, DDCSFGAM.TEMP_AMT);
        CEP.TRC(SCCGWA, DDCSFGAM.REMK);
        if (DDCSFGAM.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSFGAM.BNK.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_BK_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSFGAM.CCY.trim().length() > 0) {
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if (DDCSFGAM.FUNC == '2') {
            B020_PARM_UPREAD_PROC();
            if (pgmRtn) return;
            if (DDCSFGAM.CCY.trim().length() > 0 
                && !DDCSFGAM.CCY.equalsIgnoreCase(DDVDPAM3.CCY)) {
                DDVDPAM3.CCY = DDCSFGAM.CCY;
                WS_MODIFY_FLG = 'Y';
            }
            if (DDCSFGAM.EXR_TYP.trim().length() > 0 
                && !DDCSFGAM.EXR_TYP.equalsIgnoreCase(DDVDPAM3.EXR_TYP)) {
                DDVDPAM3.EXR_TYP = DDCSFGAM.EXR_TYP;
                WS_MODIFY_FLG = 'Y';
            }
            if (DDCSFGAM.FLG != ' ' 
                && DDCSFGAM.FLG != DDVDPAM3.FLG) {
                DDVDPAM3.FLG = DDCSFGAM.FLG;
                WS_MODIFY_FLG = 'Y';
            }
            if ((DDCSFGAM.FRG_LAMT != 0) 
                && (DDCSFGAM.FRG_LAMT != DDVDPAM3.FRG_LAMT)) {
                DDVDPAM3.FRG_LAMT = DDCSFGAM.FRG_LAMT;
                WS_MODIFY_FLG = 'Y';
            }
            if ((DDCSFGAM.TEMP_AMT != 0) 
                && (DDCSFGAM.TEMP_AMT != DDVDPAM3.TEMP_AMT)) {
                DDVDPAM3.LAST_TEMP_AMT = DDVDPAM3.TEMP_AMT;
                DDVDPAM3.TEMP_AMT = DDCSFGAM.TEMP_AMT;
                WS_MODIFY_FLG = 'Y';
            }
            if (WS_MODIFY_FLG != 'Y') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_NOT_MODIFY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MAIN_DATA_PROC() throws IOException,SQLException,Exception {
        if (DDCSFGAM.FUNC == '1') {
            B020_PARM_READ_PROC();
            if (pgmRtn) return;
            B030_OUTPUT_DATA_PROC();
            if (pgmRtn) return;
        } else if (DDCSFGAM.FUNC == '2') {
            B030_PARM_UPDATA_PROC();
            if (pgmRtn) return;
            B040_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
            B050_OUTPUT_DATA_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSFGAM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_PARM_READ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDVDPAM3);
        CEP.TRC(SCCGWA, K_PCALL_TYP);
        CEP.TRC(SCCGWA, K_PCALL_CD);
        BPRPRMT.KEY.TYP = K_PCALL_TYP;
        BPRPRMT.KEY.CD = K_PCALL_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVDPAM3);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_MSGID = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
