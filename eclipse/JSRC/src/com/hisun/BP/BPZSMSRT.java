package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMSRT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_RATEID = "RRTID";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_RTBK_AP_TYPE = "UPBKRT";
    String K_DD_SERV_CODE = "DDPEOD8";
    String K_TD_SERV_CODE = "BSPTD04";
    String K_FMT_CD_X01 = "BPX01";
    String K_FMT_CD_303 = "BP303";
    String K_FMT_CD_306 = "BP306";
    String K_FMT_CD_307 = "BP307";
    String K_FMT_CD_308 = "BP308";
    String K_HIS_COPYBOOK_RTID = "BPRRTID";
    String K_HIS_COPYBOOK_INTR = "BPRINTR";
    String K_HIS_COPYBOOK_RTFW = "BPRRTFW";
    String K_HIS_REMARKS = "BASE RATE MAINTENANCE";
    String K_HIS_REMARKS_RTID = "RATE ID   MAINTENANCE";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_PROC_DD = "DDPEOD80";
    String K_PROC_TD = "TDPEOD51";
    String K_SPEC_TENOR = "999";
    int K_BATCH_CNT = 40;
    int K_BKVAL_CNT = 1;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_CNT = 0;
    int WS_BKVAL_CNT = 0;
    String WS_RTID_CCY = " ";
    String WS_RTID_TENOR = " ";
    BPZSMSRT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMSRT_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_FILE_NAME = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRINTR BPRINTR = new BPRINTR();
    BPRRTID BPRRTID = new BPRRTID();
    BPRRTFW BPRRTFW = new BPRRTFW();
    BPRRTBK BPRRTBK = new BPRRTBK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRINTR BPRBINTR = new BPRINTR();
    BPRRTFW BPRBRTFW = new BPRRTFW();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCRINTB BPCRINTB = new BPCRINTB();
    BPCRHITM BPCRHITM = new BPCRHITM();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCO306 BPCO306 = new BPCO306();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    BPCRRTFW BPCRRTFW = new BPCRRTFW();
    BPCRRTBK BPCRRTBK = new BPCRRTBK();
    BPCRHISM BPCRHISM = new BPCRHISM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCO307 BPCO307 = new BPCO307();
    BPCO308 BPCO308 = new BPCO308();
    BPCO303 BPCO303 = new BPCO303();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMMRT BPCSMMRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMMRT BPCSMMRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMMRT = BPCSMMRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMSRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCSMMRT.BR = 999999;
        B050_BROWSE_INTR_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_INTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, BPCO306);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 196;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRINTB);
        BPCRINTB.BR = BPCSMMRT.BR;
        BPCRINTB.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPCRINTB.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPCRINTB.CCY = BPCSMMRT.CCY;
        CEP.TRC(SCCGWA, BPCSMMRT.BR);
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        WS_FILE_NAME = 'I';
        BPCRINTB.FUNC = 'S';
        if (BPCSMMRT.BASE_TYP.trim().length() == 0 
            || BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
            || BPCSMMRT.CCY.trim().length() == 0) {
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP");
                BPCRINTB.COND = '3';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-TENOR");
                BPCRINTB.COND = '4';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY");
                BPCRINTB.COND = '2';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP-TENOR");
                BPCRINTB.COND = '7';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY-BASETYP");
                BPCRINTB.COND = '5';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY-TENOR");
                BPCRINTB.COND = '6';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR");
                BPCRINTB.COND = '1';
            }
        } else {
            BPCRINTB.COND = '9';
        }
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCRINTB.COND);
        CEP.TRC(SCCGWA, BPCRINTB.BR);
        CEP.TRC(SCCGWA, BPCRINTB.CCY);
        CEP.TRC(SCCGWA, BPCRINTB.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTB.TENOR);
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        BPCRINTB.FUNC = 'N';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST) 
            && SCCMPAG.FUNC != 'E') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCRINTB.FUNC = 'N';
            S000_CALL_BPZRINTB();
            if (pgmRtn) return;
        }
        BPCRINTB.FUNC = 'E';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
        WS_FILE_NAME = 'R';
        IBS.init(SCCGWA, BPRRTFW);
        IBS.init(SCCGWA, BPCRRTFW);
        BPRRTFW.KEY.BR = BPCSMMRT.BR;
        BPRRTFW.KEY.BASE_TYP = BPCSMMRT.BASE_TYP;
        BPRRTFW.KEY.TENOR = BPCSMMRT.UPD_DATA[1-1].TENOR;
        BPRRTFW.KEY.CCY = BPCSMMRT.CCY;
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.UPD_DATA[1-1].TENOR);
        BPCRRTFW.INFO.FUNC = 'B';
        if (BPCSMMRT.BASE_TYP.trim().length() == 0 
            || BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
            || BPCSMMRT.CCY.trim().length() == 0) {
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-5");
                BPCRRTFW.INFO.OPT = '5';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-7 ");
                BPCRRTFW.INFO.OPT = '7';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-8");
                BPCRRTFW.INFO.OPT = '8';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-2");
                BPCRRTFW.INFO.OPT = '2';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() > 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-4");
                BPCRRTFW.INFO.OPT = '4';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() > 0 
                && BPCSMMRT.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-9");
                BPCRRTFW.INFO.OPT = '9';
            }
            if (BPCSMMRT.BASE_TYP.trim().length() == 0 
                && BPCSMMRT.UPD_DATA[1-1].TENOR.trim().length() == 0 
                && BPCSMMRT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RRTFW-START-1");
                BPCRRTFW.INFO.OPT = '1';
            }
        } else {
            BPCRRTFW.INFO.OPT = '3';
        }
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        CEP.TRC(SCCGWA, BPCRRTFW.RETURN_INFO);
        BPCRRTFW.INFO.OPT = 'N';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        CEP.TRC(SCCGWA, BPCRRTFW.RETURN_INFO);
        while (BPCRRTFW.RETURN_INFO != 'N') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCRRTFW.INFO.OPT = 'N';
            S000_CALL_BPZRRTFW();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRRTFW);
        BPCRRTFW.INFO.OPT = 'E';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_O_RATE_TYPE = BPCRINTB.BASE_TYP;
        if (WS_FILE_NAME == 'I') {
            CEP.TRC(SCCGWA, "BRW INTR");
            WS_OUTPUT_DATA.WS_O_TENOR = BPCRINTB.TENOR;
            WS_OUTPUT_DATA.WS_O_CCY = BPCRINTB.CCY;
            WS_OUTPUT_DATA.WS_O_EFF_DT = BPCRINTB.DT;
            WS_OUTPUT_DATA.WS_O_RATE = BPCRINTB.RATE;
        } else {
            CEP.TRC(SCCGWA, "BRW RTFW");
            WS_OUTPUT_DATA.WS_O_TENOR = BPRRTFW.KEY.TENOR;
            WS_OUTPUT_DATA.WS_O_CCY = BPRRTFW.KEY.CCY;
            WS_OUTPUT_DATA.WS_O_EFF_DT = BPRRTFW.KEY.VAL_DT;
            WS_OUTPUT_DATA.WS_O_RATE = BPRRTFW.RATE;
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_O_TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_O_TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        WS_RTID_TENOR = WS_OUTPUT_DATA.WS_O_TENOR;
        WS_RTID_CCY = WS_OUTPUT_DATA.WS_O_CCY;
        B300_01_GET_RATEID();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_O_RATE_ID = BPCOQRTD.DATA.RATE_ID;
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 101;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_01_GET_RATEID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQRTD);
        CEP.TRC(SCCGWA, BPCSMMRT.CCY);
        CEP.TRC(SCCGWA, BPCSMMRT.BASE_TYP);
        CEP.TRC(SCCGWA, WS_RTID_TENOR);
        BPCOQRTD.DATA.CCY = WS_OUTPUT_DATA.WS_O_CCY;
        BPCOQRTD.DATA.BASE_TYP = WS_OUTPUT_DATA.WS_O_RATE_TYPE;
        BPCOQRTD.DATA.TENOR = WS_OUTPUT_DATA.WS_O_TENOR;
        BPCOQRTD.INQ_FLG = 'C';
        S000_CALL_BPZPQRTD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTID-MAINT", BPCRMRTD);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FFF");
            CEP.TRC(SCCGWA, BPCOQPCD.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRRTFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTFW-MAINT", BPCRRTFW);
    }
    public void S000_CALL_BPZRRTBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTBK-MAINT", BPCRRTBK);
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-MAINT", BPCRINTM);
    }
    public void S000_CALL_BPZRHITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-MAINT", BPCRHITM);
    }
    public void S000_CALL_BPZRHISM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIS-MAINT", BPCRHISM);
    }
    public void S000_CALL_BPZRINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-BRW", BPCRINTB);
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-BRW", BPCRHITB);
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GGG");
            CEP.TRC(SCCGWA, BPCOCLWD.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
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
