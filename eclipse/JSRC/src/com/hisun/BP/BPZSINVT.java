package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSINVT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PROC_BPZRINVT = "BP-R-PROC-INVT ";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_INVNAM1 = " ";
    String WS_INVNAM2 = " ";
    String WS_HANDNAM1 = " ";
    String WS_HANDNAM2 = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRINVT BPRINVT = new BPRINVT();
    BPCTINVT BPCTINVT = new BPCTINVT();
    BPCOINVO BPCOINVO = new BPCOINVO();
    BPCONVTO BPCONVTO = new BPCONVTO();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOINVF BPCOINVF = new BPCOINVF();
    SCCGWA SCCGWA;
    BPCSINVT BPCSINVT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSINVT BPCSINVT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSINVT = BPCSINVT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSINVT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSINVT.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT1();
            if (pgmRtn) return;
        } else if (BPCSINVT.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSINVT.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSINVT.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSINVT.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.KEY.DATE = BPCSINVT.DATE;
        BPRINVT.KEY.JRNNO = BPCSINVT.JRNNO;
        BPCTINVT.INFO.FUNC = '4';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTINVT);
        IBS.init(SCCGWA, BPRINVT);
        BPCTINVT.INFO.FUNC = '5';
        CEP.TRC(SCCGWA, BPCTINVT.INFO.FUNC);
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRINVT.STS = BPCSINVT.STS;
        BPRINVT.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRINVT.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSINVT.INVNTY1);
        CEP.TRC(SCCGWA, BPCSINVT.INVNTY2);
        CEP.TRC(SCCGWA, BPCSINVT.HANDLER1);
        CEP.TRC(SCCGWA, BPCSINVT.HANDLER2);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        BPCTINVT.INFO.FUNC = '5';
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        if (BPCTINVT.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCSINVT.RC);
        }
        CEP.TRC(SCCGWA, BPRINVT);
        CEP.TRC(SCCGWA, BPRINVT.TS);
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        IBS.init(SCCGWA, BPCTINVT);
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, BPCSINVT.BR);
        CEP.TRC(SCCGWA, BPCSINVT.DATE);
        CEP.TRC(SCCGWA, BPCSINVT.DATE_END);
        CEP.TRC(SCCGWA, BPCSINVT.CB_TYP);
        BPRINVT.BR = BPCSINVT.BR;
        BPCTINVT.DATE = BPCSINVT.DATE;
        BPCTINVT.DATE_END = BPCSINVT.DATE_END;
        BPRINVT.CB_TYP = BPCSINVT.CB_TYP;
        BPRINVT.INVNTYP = '0';
        BPCTINVT.INFO.FUNC = '1';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111satrbr");
        CEP.TRC(SCCGWA, BPCSINVT.BR);
        CEP.TRC(SCCGWA, BPCSINVT.DATE);
        CEP.TRC(SCCGWA, BPRINVT);
        BPCTINVT.INFO.FUNC = '2';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRINVT);
        B050_01_OUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTINVT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B050_02_OUTPUT_DATA();
            if (pgmRtn) return;
            BPCTINVT.INFO.FUNC = '2';
            BPCTINVT.POINTER = BPRINVT;
            BPCTINVT.LEN = 409;
            CEP.TRC(SCCGWA, "CYCLE");
            S000_CALL_BPZRINVT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
        }
        BPCTINVT.INFO.FUNC = '3';
        BPCTINVT.LEN = 409;
        BPCTINVT.POINTER = BPRINVT;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "555ENDBR");
        CEP.TRC(SCCGWA, BPCTINVT.RETURN_INFO);
        if (BPCTINVT.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 909;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCONVTO);
        BPCONVTO.DATE = BPRINVT.KEY.DATE;
        BPCONVTO.JRNNO = BPRINVT.KEY.JRNNO;
        BPCONVTO.BR = BPRINVT.BR;
        BPCONVTO.TLR = BPRINVT.TX_TLR;
        BPCONVTO.INVNTY1 = BPRINVT.INVNTY;
        BPCONVTO.INVNAM1 = BPRINVT.INTY_NM;
        BPCONVTO.INVNTY2 = BPRINVT.HANDLER;
        BPCONVTO.INVNAM2 = BPRINVT.HLD_NM;
        BPCONVTO.CB_TYP = BPCSINVT.CB_TYP;
        BPCONVTO.STS = BPRINVT.STS;
        if (BPCSINVT.CB_TYP == '0') {
            BPCONVTO.CASH_STS = BPRINVT.STS;
            BPCONVTO.CCY = BPRINVT.CCY;
            BPCONVTO.MACH_AMT = BPRINVT.MACH_AMT;
            BPCONVTO.ACTU_AMT = BPRINVT.ACTU_AMT;
        }
        if (BPCSINVT.CB_TYP == '1') {
            BPCONVTO.BV_STS = BPRINVT.STS;
        }
        BPCONVTO.LAST_DT = BPRINVT.LAST_DT;
        BPCONVTO.UPD_TLR = BPRINVT.UPD_TLR;
        BPCONVTO.OPEN_DT = BPRINVT.OPEN_DT;
        BPCONVTO.OPEN_TLR = BPRINVT.OPEN_TLR;
        BPCONVTO.TS = BPRINVT.TS;
        CEP.TRC(SCCGWA, BPCONVTO.DATE);
        CEP.TRC(SCCGWA, BPCONVTO.BR);
        CEP.TRC(SCCGWA, BPCONVTO.JRNNO);
        CEP.TRC(SCCGWA, BPCONVTO.INVNTY1);
        CEP.TRC(SCCGWA, BPCONVTO.INVNTY2);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCONVTO);
        SCCMPAG.DATA_LEN = 909;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "CALL-SCSSGADR");
        SCCFMT.FMTID = BPCSINVT.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOINVO;
        SCCFMT.DATA_LEN = 5258;
        CEP.TRC(SCCGWA, BPCOINVO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_OUTPUT1() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "CALL-SCSSGADR");
        SCCFMT.FMTID = BPCSINVT.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCONVTO;
        SCCFMT.DATA_LEN = 909;
        CEP.TRC(SCCGWA, BPCOINVF);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT-BPRINVT");
        if (BPCSINVT.FUNC == 'A') {
            BPCOINVO.DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPCOINVO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            BPCOINVO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCOINVO.INVNTY1 = BPCSINVT.INVNTY1;
            BPCOINVO.INVNTY2 = BPCSINVT.INVNTY2;
            BPCOINVO.HANDLER1 = BPCSINVT.HANDLER1;
            BPCOINVO.HANDLER2 = BPCSINVT.HANDLER2;
        }
        if (BPCSINVT.FUNC == 'I') {
            IBS.init(SCCGWA, BPCOINVF);
            BPCONVTO.DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPCONVTO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            BPCONVTO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            for (WS_CNT = 1; WS_CNT <= 10 
                && BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_BV_CODE.trim().length() != 0; WS_CNT += 1) {
                BPCONVTO.BV_INFO[WS_CNT-1].MACH_BV_CODE = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_BV_CODE;
                BPCONVTO.BV_INFO[WS_CNT-1].MACH_BV_STS = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_BV_STS;
                BPCONVTO.BV_INFO[WS_CNT-1].MACH_NUM = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_NUM;
                BPCONVTO.BV_INFO[WS_CNT-1].ACTU_NUM = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].ACTU_NUM;
            }
            CEP.TRC(SCCGWA, BPCONVTO.BV_INFO[WS_CNT-1].MACH_BV_CODE);
            CEP.TRC(SCCGWA, BPCONVTO.BV_INFO[WS_CNT-1].MACH_BV_STS);
            CEP.TRC(SCCGWA, BPCONVTO.BV_INFO[WS_CNT-1].MACH_NUM);
            CEP.TRC(SCCGWA, BPCONVTO.BV_INFO[WS_CNT-1].ACTU_NUM);
        }
        CEP.TRC(SCCGWA, BPCSINVT.INVNTY2);
        CEP.TRC(SCCGWA, BPCSINVT.HANDLER1);
        CEP.TRC(SCCGWA, BPCOINVO.HANDLER2);
        CEP.TRC(SCCGWA, BPCOINVO.HANDLER2);
    }
    public void R000_TELLER_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        WS_INVNAM1 = " ";
        CEP.TRC(SCCGWA, WS_INVNAM1);
        IBS.init(SCCGWA, BPCFTLRQ);
        WS_INVNAM2 = " ";
        CEP.TRC(SCCGWA, WS_INVNAM2);
        IBS.init(SCCGWA, BPCFTLRQ);
        WS_HANDNAM1 = " ";
        CEP.TRC(SCCGWA, WS_HANDNAM1);
        IBS.init(SCCGWA, BPCFTLRQ);
        WS_HANDNAM2 = " ";
        CEP.TRC(SCCGWA, WS_HANDNAM2);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRINVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_BPZRINVT, BPCTINVT);
        CEP.TRC(SCCGWA, BPCTINVT.RC.RC_CODE);
        if (BPCTINVT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTINVT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSINVT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTINVT.RC.RC_CODE);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TLR_INF_QUERY, BPCFTLRQ);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLR_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSINVT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
