package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBOXM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String R_ADW_BOXP = "BP-R-ADW-BOXP";
    String F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    int P_CNT = 60;
    String HIS_REMARKS = "BOX PUT OUT PLAN";
    BPZSBOXM_WS_VARIABLES WS_VARIABLES = new BPZSBOXM_WS_VARIABLES();
    BPZSBOXM_WS2 WS2 = new BPZSBOXM_WS2();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOBOXD BPCOBOXD = new BPCOBOXD();
    BPCOBOXQ BPCOBOXQ = new BPCOBOXQ();
    BPCRBOXP BPCRBOXP = new BPCRBOXP();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRBOXP BPRBOXP = new BPRBOXP();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSBOXM BPCSBOXM;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBOXM BPCSBOXM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBOXM = BPCSBOXM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBOXM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCRBOXP);
        IBS.init(SCCGWA, WS2);
        IBS.init(SCCGWA, BPCOBOXD);
        IBS.init(SCCGWA, BPCOBOXQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSBOXM.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBOXM.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBOXM.FUNC == 'I') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBOXM.FUNC == 'M') {
            B040_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBOXM.FUNC == 'A' 
            || BPCSBOXM.FUNC == 'M') {
            B030_HISTORY_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P903";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        BPRBOXP.KEY.PLAN_DATE = BPCSBOXM.PLAN_DT;
        BPRBOXP.KEY.BR = BPCSBOXM.BR;
        BPCRBOXP.INFO.FUNC = '1';
        BPCRBOXP.INFO.LEN = 116;
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        S000_CALL_BPZRBOXP();
        if (pgmRtn) return;
        BPCRBOXP.INFO.FUNC = 'N';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        if (pgmRtn) return;
        B030_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_VARIABLES.I = 0;
        while (BPCRBOXP.RETURN_INFO != 'N' 
            && WS_VARIABLES.I <= 1000) {
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
            WS_VARIABLES.I = WS_VARIABLES.I + 1;
            B010_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRBOXP.INFO.FUNC = 'N';
            BPCRBOXP.INFO.POINTER = BPRBOXP;
            BPCRBOXP.INFO.LEN = 116;
            S000_CALL_BPZRBOXP();
            if (pgmRtn) return;
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= P_CNT; WS_VARIABLES.I += 1) {
            if (BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_TYPE != ' ') {
                IBS.init(SCCGWA, BPRBOXP);
                IBS.init(SCCGWA, BPCRBOXP);
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                if (BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCFTLRQ);
                    BPCFTLRQ.INFO.TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
                    S000_CALL_BPZFTLRQ();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCFTLCM);
                    BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPCFTLCM.BR = BPCFTLRQ.INFO.NEW_BR;
                    S000_CALL_BPZFTLCM();
                    if (pgmRtn) return;
                    if (BPCFTLCM.AUTH_FLG != 'Y') {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_TLR_NO_AUTH_TO_PLBR);
                    }
                }
                BPCRBOXP.INFO.POINTER = BPRBOXP;
                BPCRBOXP.INFO.LEN = 116;
                BPCRBOXP.INFO.FUNC = 'A';
                S000_CALL_BPZRBOXP();
                if (pgmRtn) return;
                if (BPCRBOXP.RC.RC_CODE != 0) {
                    WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBOXP.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                R010_TRANS_DATA_OUPUT();
                if (pgmRtn) return;
            }
        }
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBOXP);
        CEP.TRC(SCCGWA, BPCSBOXM.PLAN_DT);
        CEP.TRC(SCCGWA, BPCSBOXM.BR);
        BPRBOXP.KEY.PLAN_DATE = BPCSBOXM.PLAN_DT;
        BPRBOXP.KEY.BR = BPCSBOXM.BR;
        BPCRBOXP.INFO.FUNC = '2';
        BPCRBOXP.INFO.LEN = 116;
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        S000_CALL_BPZRBOXP();
        if (pgmRtn) return;
        BPCRBOXP.INFO.FUNC = 'N';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        if (pgmRtn) return;
        WS_VARIABLES.I = 0;
        for (WS_VARIABLES.I = 1; BPCRBOXP.RETURN_INFO != 'N' 
            && WS_VARIABLES.I <= P_CNT; WS_VARIABLES.I += 1) {
            CEP.TRC(SCCGWA, "22222");
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOBOXQ.PLAN_DATE);
            CEP.TRC(SCCGWA, BPCOBOXQ.BR);
            CEP.TRC(SCCGWA, BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].BOX_TYPE);
            CEP.TRC(SCCGWA, BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].BOX_NO);
            CEP.TRC(SCCGWA, BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].CRNT_TLR);
            CEP.TRC(SCCGWA, BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].PLAN_TLR);
            CEP.TRC(SCCGWA, BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].RECV_FLG);
            BPCRBOXP.INFO.FUNC = 'N';
            BPCRBOXP.INFO.POINTER = BPRBOXP;
            BPCRBOXP.INFO.LEN = 116;
            S000_CALL_BPZRBOXP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LYY");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBOXM.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBOXQ;
        SCCFMT.DATA_LEN = 1454;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOBOXQ.PLAN_DATE);
        CEP.TRC(SCCGWA, BPCOBOXQ.BR);
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= P_CNT 
            && BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_TYPE != ' '; WS_VARIABLES.I += 1) {
            BPRBOXP.KEY.PLAN_DATE = BPCSBOXM.PLAN_DT;
            BPRBOXP.KEY.BR = BPCSBOXM.BR;
            BPRBOXP.KEY.BOX_TYPE = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_TYPE;
            BPRBOXP.KEY.BOX_NO = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_NO;
            BPCRBOXP.INFO.POINTER = BPRBOXP;
            BPCRBOXP.INFO.LEN = 116;
            BPCRBOXP.INFO.FUNC = 'R';
            S000_CALL_BPZRBOXP();
            if (pgmRtn) return;
            if (BPCRBOXP.RETURN_INFO == 'N') {
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                if (BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCFTLRQ);
                    BPCFTLRQ.INFO.TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
                    S000_CALL_BPZFTLRQ();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCFTLCM);
                    BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPCFTLCM.BR = BPCFTLRQ.INFO.NEW_BR;
                    S000_CALL_BPZFTLCM();
                    if (pgmRtn) return;
                    if (BPCFTLCM.AUTH_FLG != 'Y') {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_TLR_NO_AUTH_TO_PLBR);
                    }
                }
                BPCRBOXP.INFO.POINTER = BPRBOXP;
                BPCRBOXP.INFO.LEN = 116;
                BPCRBOXP.INFO.FUNC = 'A';
                S000_CALL_BPZRBOXP();
                if (pgmRtn) return;
                if (BPCRBOXP.RC.RC_CODE != 0) {
                    WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBOXP.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
                if (BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].CRNT_TLR.equalsIgnoreCase(BPRBOXP.CRNT_TLR) 
                    && BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR.equalsIgnoreCase(BPRBOXP.PLAN_TLR)) {
                } else {
                    if (BPRBOXP.RECV_FLG == 'Y') {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CANT_MODIFY_PLAN_TLR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR.trim().length() > 0) {
                        IBS.init(SCCGWA, BPCFTLRQ);
                        BPCFTLRQ.INFO.TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
                        S000_CALL_BPZFTLRQ();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, BPCFTLCM);
                        BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
                        BPCFTLCM.BR = BPCFTLRQ.INFO.NEW_BR;
                        S000_CALL_BPZFTLCM();
                        if (pgmRtn) return;
                        if (BPCFTLCM.AUTH_FLG != 'Y') {
                            CEP.ERR(SCCGWA, ERROR_MSG.BP_TLR_NO_AUTH_TO_PLBR);
                        }
                    }
                    BPRBOXP.PLAN_TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
                    BPRBOXP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRBOXP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPRBOXP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                    BPCRBOXP.INFO.POINTER = BPRBOXP;
                    BPCRBOXP.INFO.LEN = 116;
                    BPCRBOXP.INFO.FUNC = 'U';
                    S000_CALL_BPZRBOXP();
                    if (pgmRtn) return;
                }
            }
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRBOXP.KEY.PLAN_DATE = BPCSBOXM.PLAN_DT;
        BPRBOXP.KEY.BR = BPCSBOXM.BR;
        BPRBOXP.KEY.BOX_TYPE = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_TYPE;
        BPRBOXP.KEY.BOX_NO = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_NO;
        BPRBOXP.CRNT_TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].CRNT_TLR;
        BPRBOXP.PLAN_TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
        BPRBOXP.RECV_FLG = 'N';
        BPRBOXP.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBOXP.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.OPEN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRBOXP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBOXP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B010_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS2);
        SCCMPAG.DATA_LEN = 14;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRBOXP.INFO.FUNC = 'E';
        BPCRBOXP.INFO.LEN = 116;
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        S000_CALL_BPZRBOXP();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBOXM.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBOXD;
        SCCFMT.DATA_LEN = 1394;
        CEP.TRC(SCCGWA, BPCOBOXD);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT");
        if (BPCSBOXM.FUNC == 'B') {
            WS2.PLAN_DATE = BPRBOXP.KEY.PLAN_DATE;
            WS2.BR = BPRBOXP.KEY.BR;
        }
        if (BPCSBOXM.FUNC == 'A' 
            || BPCSBOXM.FUNC == 'M') {
            BPCOBOXD.PLAN_DATE = BPCSBOXM.PLAN_DT;
            BPCOBOXD.BR = BPCSBOXM.BR;
            BPCOBOXD.DATA.PLAN[WS_VARIABLES.I-1].BOX_TYPE = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_TYPE;
            BPCOBOXD.DATA.PLAN[WS_VARIABLES.I-1].BOX_NO = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].BOX_NO;
            BPCOBOXD.DATA.PLAN[WS_VARIABLES.I-1].CRNT_TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].CRNT_TLR;
            BPCOBOXD.DATA.PLAN[WS_VARIABLES.I-1].PLAN_TLR = BPCSBOXM.PLAN_INFO[WS_VARIABLES.I-1].PLAN_TLR;
        }
        if (BPCSBOXM.FUNC == 'I') {
            BPCOBOXQ.PLAN_DATE = BPRBOXP.KEY.PLAN_DATE;
            BPCOBOXQ.BR = BPRBOXP.KEY.BR;
            BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].BOX_TYPE = BPRBOXP.KEY.BOX_TYPE;
            BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].BOX_NO = BPRBOXP.KEY.BOX_NO;
            BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].CRNT_TLR = BPRBOXP.CRNT_TLR;
            BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].PLAN_TLR = BPRBOXP.PLAN_TLR;
            BPCOBOXQ.DATA.PLAN[WS_VARIABLES.I-1].RECV_FLG = BPRBOXP.RECV_FLG;
            CEP.TRC(SCCGWA, "DEV");
        }
    }
    public void S000_CALL_BPZRBOXP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_ADW_BOXP, BPCRBOXP);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void B030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 14;
        SCCMPAG.SCR_ROW_CNT = (short) P_CNT;
        SCCMPAG.SCR_COL_CNT = 2;
        B_MPAG();
        if (pgmRtn) return;
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
