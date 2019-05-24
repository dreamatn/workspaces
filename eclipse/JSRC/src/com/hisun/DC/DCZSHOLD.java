package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSHOLD {
    DBParm DDTHLD_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC770";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCOHOLD DCCOHOLD = new DCCOHOLD();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    SCCGWA SCCGWA;
    DCCSHOLD DCCSHOLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSHOLD DCCSHOLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSHOLD = DCCSHOLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSHOLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_AC_HOLD_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCSHOLD.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.HLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.HLD_TYP != '1' 
            && DCCSHOLD.DATA.HLD_TYP != '2' 
            && DCCSHOLD.DATA.HLD_TYP != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP != '1' 
            && DCCSHOLD.DATA.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSHOLD.DATA.HLD_TYP == '2' 
            || DCCSHOLD.DATA.HLD_TYP == 'A') 
            && DCCSHOLD.DATA.AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP == '1' 
            && DCCSHOLD.DATA.SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP == '1' 
            && DCCSHOLD.DATA.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP == '1' 
            && DCCSHOLD.DATA.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.SPR_TYP == '1' 
            && DCCSHOLD.DATA.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSHOLD.DATA.HLD_TYP);
        CEP.TRC(SCCGWA, DCCSHOLD.DATA.REF_OLD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (DCCSHOLD.DATA.HLD_TYP == 'A' 
            && DCCSHOLD.DATA.REF_OLD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSHOLD.DATA.HLD_FLG != ' ' 
            && DCCSHOLD.DATA.HLD_FLG != '1' 
            && DCCSHOLD.DATA.HLD_FLG != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_FLG_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_AC_HOLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        if (DCCSHOLD.DATA.HLD_TYP != 'A') {
            DCCUHLD.DATA.AC = DCCSHOLD.DATA.AC;
            DCCUHLD.DATA.SEQ = DCCSHOLD.DATA.SEQ;
            DCCUHLD.DATA.HLD_TYP = DCCSHOLD.DATA.HLD_TYP;
            DCCUHLD.DATA.SPR_TYP = DCCSHOLD.DATA.SPR_TYP;
            DCCUHLD.DATA.CCY = DCCSHOLD.DATA.CCY;
            DCCUHLD.DATA.CCY_TYP = DCCSHOLD.DATA.CCY_TYP;
            DCCUHLD.DATA.AMT = DCCSHOLD.DATA.AMT;
            DCCUHLD.DATA.SPR_NO = DCCSHOLD.DATA.SPR_NO;
            DCCUHLD.DATA.SPR_NM = DCCSHOLD.DATA.SPR_NM;
            DCCUHLD.DATA.RSN = DCCSHOLD.DATA.RSN;
            DCCUHLD.DATA.EFF_DT = DCCSHOLD.DATA.EFF_DT;
            DCCUHLD.DATA.EXP_DT = DCCSHOLD.DATA.EXP_DT;
            DCCUHLD.DATA.RMK = DCCSHOLD.DATA.RMK;
            DCCUHLD.DATA.HLD_BR = DCCSHOLD.DATA.HLD_BR;
            DCCUHLD.DATA.LAW_NM1 = DCCSHOLD.DATA.LAW_NM1;
            DCCUHLD.DATA.LAW_NO1 = DCCSHOLD.DATA.LAW_NO1;
            DCCUHLD.DATA.LAW_NM2 = DCCSHOLD.DATA.LAW_NM2;
            DCCUHLD.DATA.LAW_NO2 = DCCSHOLD.DATA.LAW_NO2;
            DCCUHLD.DATA.HLD_NO = DCCSHOLD.DATA.REF_OLD;
            DCCUHLD.DATA.HLD_CLS = DCCSHOLD.DATA.HLD_CLS;
            DCCUHLD.DATA.CHK_OPT = DCCSHOLD.DATA.CHK_OPT;
            DCCUHLD.DATA.PSWD = DCCSHOLD.DATA.PSWD;
            DCCUHLD.DATA.TRK_DAT2 = DCCSHOLD.DATA.TRK_DAT2;
            DCCUHLD.DATA.TRK_DAT3 = DCCSHOLD.DATA.TRK_DAT3;
            DCCUHLD.DATA.HLD_FLG = DCCSHOLD.DATA.HLD_FLG;
            if (DCCSHOLD.DATA.HLD_FLG == ' ') {
                DCCUHLD.DATA.HLD_FLG = '1';
            }
        } else {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = DCCSHOLD.DATA.REF_OLD;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            DCCUHLD.DATA.HLD_NO = DCCSHOLD.DATA.REF_OLD;
            DCCUHLD.DATA.AC = DCCSHOLD.DATA.AC;
            DCCUHLD.DATA.ACO = DDRHLD.AC;
            DCCUHLD.DATA.SEQ = DCCSHOLD.DATA.SEQ;
            DCCUHLD.DATA.HLD_TYP = DCCSHOLD.DATA.HLD_TYP;
            DCCUHLD.DATA.SPR_TYP = DDRHLD.SPR_BR_TYP;
            DCCUHLD.DATA.CCY = DCCSHOLD.DATA.CCY;
            DCCUHLD.DATA.CCY_TYP = DCCSHOLD.DATA.CCY_TYP;
            DCCUHLD.DATA.AMT = DCCSHOLD.DATA.AMT;
            DCCUHLD.DATA.SPR_NO = DDRHLD.HLD_WRIT_NO;
            DCCUHLD.DATA.SPR_NM = DDRHLD.HLD_BR_NM;
            DCCUHLD.DATA.RSN = DDRHLD.HLD_RSN;
            DCCUHLD.DATA.EFF_DT = DDRHLD.EFF_DATE;
            DCCUHLD.DATA.EXP_DT = DCCSHOLD.DATA.EXP_DT;
            DCCUHLD.DATA.RMK = DDRHLD.REMARK;
            DCCUHLD.DATA.HLD_BR = DCCSHOLD.DATA.HLD_BR;
            DCCUHLD.DATA.LAW_NM1 = DDRHLD.LAW_OFF_NM1;
            DCCUHLD.DATA.LAW_NO1 = DDRHLD.LAW_ID_NO1;
            DCCUHLD.DATA.LAW_NM2 = DDRHLD.LAW_OFF_NM2;
            DCCUHLD.DATA.LAW_NO2 = DDRHLD.LAW_ID_NO2;
            DCCUHLD.DATA.CHK_OPT = DCCSHOLD.DATA.CHK_OPT;
            DCCUHLD.DATA.PSWD = DCCSHOLD.DATA.PSWD;
            DCCUHLD.DATA.TRK_DAT2 = DCCSHOLD.DATA.TRK_DAT2;
            DCCUHLD.DATA.TRK_DAT3 = DCCSHOLD.DATA.TRK_DAT3;
            DCCUHLD.DATA.HLD_FLG = DDRHLD.HLD_FLG;
        }
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOHOLD);
        DCCOHOLD.DATA.HLD_NO = DCCUHLD.DATA.HLD_NO;
        DCCOHOLD.DATA.AC = DCCUHLD.DATA.AC;
        DCCOHOLD.DATA.SEQ = DCCUHLD.DATA.SEQ;
        DCCOHOLD.DATA.HLD_TYP = DCCUHLD.DATA.HLD_TYP;
        DCCOHOLD.DATA.SPR_TYP = DCCUHLD.DATA.SPR_TYP;
        DCCOHOLD.DATA.CCY = DCCUHLD.DATA.CCY;
        DCCOHOLD.DATA.CCY_TYP = DCCUHLD.DATA.CCY_TYP;
        DCCOHOLD.DATA.AMT = DCCUHLD.DATA.AMT;
        DCCOHOLD.DATA.SPR_NO = DCCUHLD.DATA.SPR_NO;
        DCCOHOLD.DATA.SPR_NM = DCCUHLD.DATA.SPR_NM;
        DCCOHOLD.DATA.RSN = DCCUHLD.DATA.RSN;
        DCCOHOLD.DATA.EFF_DT = DCCUHLD.DATA.EFF_DT;
        DCCOHOLD.DATA.EXP_DT = DCCUHLD.DATA.EXP_DT;
        DCCOHOLD.DATA.RMK = DCCUHLD.DATA.RMK;
        DCCOHOLD.DATA.HLD_BR = DCCUHLD.DATA.HLD_BR;
        DCCOHOLD.DATA.LAW_NM1 = DCCUHLD.DATA.LAW_NM1;
        DCCOHOLD.DATA.LAW_NO1 = DCCUHLD.DATA.LAW_NO1;
        DCCOHOLD.DATA.LAW_NM2 = DCCUHLD.DATA.LAW_NM2;
        DCCOHOLD.DATA.LAW_NO2 = DCCUHLD.DATA.LAW_NO2;
        DCCOHOLD.DATA.AAMT = DCCUHLD.DATA.AAMT;
        DCCOHOLD.DATA.CURR_BAL = DCCUHLD.DATA.CURR_BAL;
        DCCOHOLD.DATA.HAMT = DCCUHLD.DATA.UAMT;
        DCCOHOLD.DATA.HLD_FLG = DCCUHLD.DATA.HLD_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOHOLD;
        SCCFMT.DATA_LEN = 1375;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = "" + SCCGWA.COMM_AREA.DBIO_FLG;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
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
