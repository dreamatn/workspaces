package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSRHLD {
    DBParm DDTHLD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC751";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCORHLD DCCORHLD = new DCCORHLD();
    SCCBINF SCCBINF = new SCCBINF();
    DDRHLD DDRHLD = new DDRHLD();
    SCCGWA SCCGWA;
    DCCSRHLD DCCSRHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSRHLD DCCSRHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSRHLD = DCCSRHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSRHLD return!");
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
        B020_AC_REL_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RHLD_TYP);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RAMT);
        if (DCCSRHLD.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSRHLD.DATA.RHLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSRHLD.DATA.RHLD_TYP != '1' 
            && DCCSRHLD.DATA.RHLD_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCSRHLD.DATA.RHLD_TYP == '2' 
            && DCCSRHLD.DATA.RAMT == 0)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
        } else {
            IBS.init(SCCGWA, DDRHLD);
            CEP.TRC(SCCGWA, DCCSRHLD.DATA.HLD_NO);
            DDRHLD.KEY.HLD_NO = DCCSRHLD.DATA.HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRHLD.HLD_CLS);
            CEP.TRC(SCCGWA, DDRHLD.SPR_CHNL);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (DDRHLD.HLD_CLS == '3' 
                || DDRHLD.HLD_CLS == '7' 
                || DDRHLD.HLD_CLS == '9' 
                || DDRHLD.HLD_CLS == 'B') {
                CEP.TRC(SCCGWA, "=====ERR=====");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_CLS_NOT_RLS);
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 706660500 
                && DDRHLD.HLD_CLS == '2') {
                CEP.TRC(SCCGWA, "==706660500==");
            } else {
                if (DDRHLD.HLD_CLS != ' ' 
                    && DDRHLD.SPR_CHNL.trim().length() > 0 
                    && !DDRHLD.SPR_CHNL.equalsIgnoreCase(SCCGWA.COMM_AREA.REQ_SYSTEM)) {
                    CEP.TRC(SCCGWA, "=====SYSTEM ERR=====");
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SYSTEM_NOT_SAME);
                }
            }
        }
    }
    public void B020_AC_REL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = DCCSRHLD.DATA.HLD_NO;
        DCCURHLD.DATA.RHLD_TYP = DCCSRHLD.DATA.RHLD_TYP;
        DCCURHLD.DATA.RAMT = DCCSRHLD.DATA.RAMT;
        DCCURHLD.DATA.CHG_NO = DCCSRHLD.DATA.CHG_NO;
        DCCURHLD.DATA.SPR_NM = DCCSRHLD.DATA.SPR_NM;
        DCCURHLD.DATA.RSN = DCCSRHLD.DATA.RSN;
        DCCURHLD.DATA.RMK = DCCSRHLD.DATA.RMK;
        DCCURHLD.DATA.CHG_BR = DCCSRHLD.DATA.CHG_BR;
        DCCURHLD.DATA.LAW_NM1 = DCCSRHLD.DATA.LAW_NM1;
        DCCURHLD.DATA.LAW_NO1 = DCCSRHLD.DATA.LAW_NO1;
        DCCURHLD.DATA.LAW_NM2 = DCCSRHLD.DATA.LAW_NM2;
        DCCURHLD.DATA.LAW_NO2 = DCCSRHLD.DATA.LAW_NO2;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RHLD_TYP);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RAMT);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.CHG_NO);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.SPR_NM);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RSN);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.RMK);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.CHG_BR);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.LAW_NM1);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.LAW_NO1);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.LAW_NM2);
        CEP.TRC(SCCGWA, DCCSRHLD.DATA.LAW_NO2);
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCORHLD);
        DCCORHLD.DATA.HLD_NO = DCCURHLD.DATA.HLD_NO;
        DCCORHLD.DATA.AC = DCCURHLD.DATA.AC;
        DCCORHLD.DATA.HLD_TYP = DCCURHLD.DATA.HLD_TYP;
        DCCORHLD.DATA.SPR_TYP = DCCURHLD.DATA.SPR_TYP;
        DCCORHLD.DATA.CCY = DCCURHLD.DATA.CCY;
        DCCORHLD.DATA.CCY_TYP = DCCURHLD.DATA.CCY_TYP;
        DCCORHLD.DATA.BEF_AMT = DCCURHLD.DATA.BEF_AMT;
        DCCORHLD.DATA.EFF_DT = DCCURHLD.DATA.EFF_DT;
        DCCORHLD.DATA.EXP_DT = DCCURHLD.DATA.EXP_DT;
        DCCORHLD.DATA.RHLD_TYP = DCCURHLD.DATA.RHLD_TYP;
        DCCORHLD.DATA.RAMT = DCCURHLD.DATA.RAMT;
        DCCORHLD.DATA.REM_AMT = DCCURHLD.DATA.REM_AMT;
        DCCORHLD.DATA.CHG_NO = DCCURHLD.DATA.CHG_NO;
        DCCORHLD.DATA.SPR_NM = DCCURHLD.DATA.SPR_NM;
        DCCORHLD.DATA.RSN = DCCURHLD.DATA.RSN;
        DCCORHLD.DATA.RMK = DCCURHLD.DATA.RMK;
        DCCORHLD.DATA.CHG_BR = DCCURHLD.DATA.CHG_BR;
        DCCORHLD.DATA.LAW_NM1 = DCCURHLD.DATA.LAW_NM1;
        DCCORHLD.DATA.LAW_NO1 = DCCURHLD.DATA.LAW_NO1;
        DCCORHLD.DATA.LAW_NM2 = DCCURHLD.DATA.LAW_NM2;
        DCCORHLD.DATA.LAW_NO2 = DCCURHLD.DATA.LAW_NO2;
        DCCORHLD.DATA.CURR_BAL = DCCURHLD.DATA.CURR_BAL;
        DCCORHLD.DATA.AVL_AMT = DCCURHLD.DATA.AVL_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCORHLD;
        SCCFMT.DATA_LEN = 1370;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.AC);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.HLD_TYP);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.SPR_TYP);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.CCY);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.CCY_TYP);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.BEF_AMT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.EFF_DT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.EXP_DT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.RHLD_TYP);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.RAMT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.REM_AMT);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.CHG_NO);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.SPR_NM);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.RSN);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.RMK);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.CHG_BR);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.LAW_NM1);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.LAW_NO1);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.LAW_NM2);
        CEP.TRC(SCCGWA, DCCORHLD.DATA.LAW_NO2);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.CURR_BAL);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
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
