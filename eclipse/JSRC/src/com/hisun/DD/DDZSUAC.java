package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICMACR;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACR;
import com.hisun.CI.CICQCIAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZSUAC {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD037";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "0001";
    String K_HIS_REMARKS = "CHANGE ACCOUNT INFORMATION";
    String K_HIS_CPB_NM = "DDCHUAC";
    String K_AGT_TYP = "141200001008";
    char K_ENTY_TYP = '1';
    String WS_ERR_MSG = " ";
    DDZSUAC_WS_MMDD WS_MMDD = new DDZSUAC_WS_MMDD();
    short WS_SIGN_TYP = 0;
    short WS_STC_SEQ = 0;
    short WS_ACTYP_C = 0;
    short WS_NUM_D = 0;
    short WS_NUM_C = 0;
    short WS_I = 0;
    char WS_MST_FLG = ' ';
    char WS_INF_FLG = ' ';
    char WS_MST_CHG = ' ';
    char WS_INF_CHG = ' ';
    char WS_CITACR_CHG = ' ';
    char WS_OPENNO_CHG = ' ';
    char WS_SCHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    DDCHUAC DDCHUACO = new DDCHUAC();
    DDCHUAC DDCHUACN = new DDCHUAC();
    DDCHUAC DDCHUAC = new DDCHUAC();
    DDCOUAC DDCOUAC = new DDCOUAC();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    CICSSTC CICSSTC = new CICSSTC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACR CICSACR = new CICSACR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCPBROP DDCPBROP = new DDCPBROP();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACR CICQACR = new CICQACR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDRMST1 = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRINF DDRINF1 = new DDRINF();
    DDRCCY DDRCCY = new DDRCCY();
    DDRSCHQ DDRSCHQ = new DDRSCHQ();
    CICMACR CICMACR = new CICMACR();
    SCCGWA SCCGWA;
    DDCSUAC DDCSUAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSUAC DDCSUAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSUAC = DDCSUAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSUAC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_GET_CI_PROC();
        if (pgmRtn) return;
        B030_GET_MST_PROC();
        if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("29050")) {
            B040_UPD_CITACR_PROC();
            if (pgmRtn) return;
            B080_UPD_MST_PROC();
            if (pgmRtn) return;
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400") 
                && !JIBS_tmp_str[1].equalsIgnoreCase("29050")) {
            B080_UPD_MST_PROC();
            if (pgmRtn) return;
            B070_UPD_INF_PROC();
            if (pgmRtn) return;
        } else {
            B045_CHK_CAL_INT_FLG();
            if (pgmRtn) return;
            B048_UPD_CITACR_PROC();
            if (pgmRtn) return;
            B050_UPD_MST_PROC();
            if (pgmRtn) return;
            B060_UPD_INF_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DDRMST1.FRG_IND);
        B070_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B080_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DDCSUAC.BASIC_INF.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400")) {
            B010_CHECK_DATA_VALIDITY();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSUAC.BASIC_INF.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("115220")) {
            if (DDCSUAC.BASIC_INF.CHK_IND == '1' 
                && DDCSUAC.COMP_INF.CHK_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4012;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSUAC.COMP_INF.VER_TEL1.trim().length() == 0 
            && DDCSUAC.COMP_INF.VER_CELL1.trim().length() == 0 
            && DDCSUAC.COMP_INF.FLG != 'Y') {
            CEP.TRC(SCCGWA, "YYYYYY");
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OSA_BR);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OSA_BRN);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OSA_MGR);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OSA_MGR_TEL);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_TYPE1);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_NAME1);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_POS1);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_TEL1);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_CELL1);
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.VER_TYPE2);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEL_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSUAC.COMP_INF.VER_TYPE2 != ' ') {
            if (DDCSUAC.COMP_INF.VER_NAME2.trim().length() == 0 
                && DDCSUAC.COMP_INF.FLG != 'Y') {
                CEP.TRC(SCCGWA, "LLLLLL");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VER_NAM2_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSUAC.COMP_INF.VER_TEL2.trim().length() == 0 
                && DDCSUAC.COMP_INF.VER_CELL2.trim().length() == 0) {
                CEP.TRC(SCCGWA, "FFFFFF");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEL_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_GET_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSUAC.BASIC_INF.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUST_F_JUS_FF);
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_CLOSE);
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        if (!DDCSUAC.BASIC_INF.CCY.equalsIgnoreCase("156") 
            && DDCSUAC.BASIC_INF.CCY.trim().length() > 0 
            && CICACCU.DATA.CI_TYP != '3' 
            && DDCSUAC.COMP_INF.FRG_OPNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_OP_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSUAC.BASIC_INF.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
        B020_UPD_BPOCAC_PROC();
        if (pgmRtn) return;
    }
    public void B020_UPD_BPOCAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OPEN_NO);
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCSOCAC.AC = DDCSUAC.BASIC_INF.AC_NO;
        BPCSOCAC.STS = 'N';
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400")) {
            BPCSOCAC.OPEN_NO = DDCSUAC.COMP_INF.OPEN_NO;
        }
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400")) {
            CEP.TRC(SCCGWA, DDCSUAC.COMP_INF.OPEN_NO);
            CEP.TRC(SCCGWA, BPCSOCAC.OPEN_NO);
            if (DDCSUAC.COMP_INF.OPEN_NO.equalsIgnoreCase(BPCSOCAC.OPEN_NO)) {
                WS_OPENNO_CHG = 'N';
            }
        }
    }
    public void B030_GET_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSUAC.BASIC_INF.AC_NO;
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_NO);
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        if (WS_MST_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_CHK_AC_STS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        if (DDRMST.CROS_DR_FLG == '2' 
            && (DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ON_OPBR_CAN_MOD_ACIF);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        CEP.TRC(SCCGWA, DDRMST.FRG_IND.substring(0, 2));
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        if ((!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) 
            && (!DDRMST.FRG_IND.substring(0, 2).equalsIgnoreCase("FT"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ON_FTA_NO_MOD_FTA);
        }
        IBS.CLONE(SCCGWA, DDRMST, DDRMST1);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400") 
            || JIBS_tmp_str[1].equalsIgnoreCase("29050")) {
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_TYPE);
            if (DDRMST.AC_TYPE == 'G' 
                || DDRMST.AC_TYPE == 'H') {
                if (DDCSUAC.BASIC_INF.AC_TYPE == 'G' 
                    || DDCSUAC.BASIC_INF.AC_TYPE == 'H') {
                } else {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FRG_AC_TYPE_NOT_MODE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (DDCSUAC.BASIC_INF.AC_TYPE == 'G' 
                    || DDCSUAC.BASIC_INF.AC_TYPE == 'H') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FRG_AC_TYPE_NOT_MODE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            IBS.init(SCCGWA, DDCPBROP);
            BPRPRMT.KEY.TYP = "PDD21";
            BPRPRMT.KEY.CD = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPBROP.DATA_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            BPCPRMM.DAT_PTR = BPRPRMT;
            BPCPRMM.FUNC = '3';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            DDCPBROP.DATA_TXT.COM_FLG = JIBS_tmp_str[0].charAt(0);
            CEP.TRC(SCCGWA, DDCPBROP.DATA_TXT);
            CEP.TRC(SCCGWA, DDCPBROP.DATA_TXT.COM_FLG);
            CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_TYPE);
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDCSUAC.BASIC_INF.AC_TYPE != DDRMST.AC_TYPE 
                && (DDCSUAC.BASIC_INF.AC_TYPE == 'C' 
                || DDCSUAC.BASIC_INF.AC_TYPE == 'D')) {
                IBS.init(SCCGWA, CICQCIAC);
                CICQCIAC.FUNC = '1';
                CICQCIAC.DATA.CI_NO = CICACCU.DATA.CI_NO;
                CICQCIAC.DATA.FRM_APP = "DD";
                S000_CALL_CIZQCIAC();
                if (pgmRtn) return;
                for (WS_I = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO.trim().length() != 0 
                    && WS_I <= 100; WS_I += 1) {
                    IBS.init(SCCGWA, DDRMST);
                    DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
                    CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
                    T000_READ_DDTMST();
                    if (pgmRtn) return;
                    if (!DDRMST.KEY.CUS_AC.equalsIgnoreCase(DDCSUAC.BASIC_INF.AC_NO)) {
                        CEP.TRC(SCCGWA, DDRMST.AC_STS);
                        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.AC_TYPE);
                        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
                        if (DDCSUAC.BASIC_INF.AC_TYPE == 'C' 
                            && DDRMST.AC_TYPE == 'C' 
                            && DDRMST.AC_STS != 'C') {
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_4032);
                        }
                        if (DDCSUAC.BASIC_INF.AC_TYPE == 'C' 
                            && DDRMST.OWNER_BRDP == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                            && DDRMST.AC_TYPE == 'D' 
                            && DDRMST.AC_STS != 'C') {
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.CUS_HAVE_D_AC);
                        }
                        if (DDCSUAC.BASIC_INF.AC_TYPE == 'D' 
                            && DDRMST.AC_TYPE == 'C' 
                            && DDRMST.AC_STS != 'C' 
                            && DDRMST.OWNER_BRDP == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.CUS_HAVE_C_AC);
                        }
                    }
                }
                IBS.CLONE(SCCGWA, DDRMST1, DDRMST);
            }
        }
        IBS.init(SCCGWA, DDRINF);
        DDRINF.KEY.CUS_AC = DDCSUAC.BASIC_INF.AC_NO;
        T000_READ_UPDATE_DDTINF();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRINF, DDRINF1);
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
    }
    public void B045_CHK_CAL_INT_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.CR_FLG);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CROS_CR_LMT);
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.DR_FLG);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CROS_DR_LMT);
        if ((DDCSUAC.BASIC_INF.CR_FLG == '1' 
            && DDVMPRD.VAL.CROS_CR_LMT != '1') 
            || (DDCSUAC.BASIC_INF.CR_FLG != '2' 
            && DDVMPRD.VAL.CROS_CR_LMT == '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4034;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DDCSUAC.BASIC_INF.DR_FLG == '1' 
            && DDVMPRD.VAL.CROS_DR_LMT != '1') 
            || (DDCSUAC.BASIC_INF.DR_FLG != '2' 
            && DDVMPRD.VAL.CROS_DR_LMT == '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DR_FLG_SURPASS_PRODUCT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CASH_TXN_TYPE);
        CEP.TRC(SCCGWA, DDCSUAC.BASIC_INF.CASH_FLG);
        if (DDVMPRD.VAL.CASH_TXN_TYPE != '1') {
            if ((DDVMPRD.VAL.CASH_TXN_TYPE == '4' 
                && DDCSUAC.BASIC_INF.CASH_FLG != '4') 
                || (DDVMPRD.VAL.CASH_TXN_TYPE == '2' 
                && (DDCSUAC.BASIC_INF.CASH_FLG != '2' 
                && DDCSUAC.BASIC_INF.CASH_FLG != '4')) 
                || (DDVMPRD.VAL.CASH_TXN_TYPE == '3' 
                && (DDCSUAC.BASIC_INF.CASH_FLG != '3' 
                && DDCSUAC.BASIC_INF.CASH_FLG != '4'))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_F_CURR_NO_CASH_TXN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_UPD_MST_PROC() throws IOException,SQLException,Exception {
        WS_MST_CHG = 'N';
        R000_TRANS_MST_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRMST.PRDAC_CD);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.CARD_FLG);
        CEP.TRC(SCCGWA, DDRMST.FRG_TYPE);
        CEP.TRC(SCCGWA, DDRMST.FRG_CODE);
        CEP.TRC(SCCGWA, DDRMST.FRG_OPEN_NO);
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST1.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRMST1.PROD_CODE);
        CEP.TRC(SCCGWA, DDRMST1.AC_STS);
        CEP.TRC(SCCGWA, DDRMST1.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRMST1.PRDAC_CD);
        CEP.TRC(SCCGWA, DDRMST1.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST1.CARD_FLG);
        CEP.TRC(SCCGWA, DDRMST1.FRG_TYPE);
        CEP.TRC(SCCGWA, DDRMST1.FRG_CODE);
        CEP.TRC(SCCGWA, DDRMST1.FRG_OPEN_NO);
        CEP.TRC(SCCGWA, DDRMST1.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST1.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST1.CHCK_IND);
        CEP.TRC(SCCGWA, DDRMST1.PBC_APPR_DATE);
        CEP.TRC(SCCGWA, DDRMST.PBC_APPR_DATE);
