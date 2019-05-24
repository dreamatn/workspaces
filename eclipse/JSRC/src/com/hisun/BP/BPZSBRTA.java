package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBRTA {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String BP_HIS_REMARKS = "MESSAGE MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_REC_CNT = 0;
    char WS_FILE_NAME = ' ';
    char WS_EOF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTFW BPRRTFW = new BPRRTFW();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCOBRTA BPCOBRTA = new BPCOBRTA();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    BPCRRTFW BPCRRTFW = new BPCRRTFW();
    BPCRINTB BPCRINTB = new BPCRINTB();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCGWA SCCGWA;
    BPCSBRTA BPCSBRTA;
    public void MP(SCCGWA SCCGWA, BPCSBRTA BPCSBRTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBRTA = BPCSBRTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBRTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        IBS.init(SCCGWA, BPCOBRTA);
        IBS.init(SCCGWA, BPCOQRTD);
        CEP.TRC(SCCGWA, BPCSBRTA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSBRTA.BASE_TYP.trim().length() == 0 
            && BPCSBRTA.CCY.trim().length() == 0 
            && BPCSBRTA.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '1';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() == 0 
            && BPCSBRTA.CCY.trim().length() > 0 
            && BPCSBRTA.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '2';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() > 0 
            && BPCSBRTA.CCY.trim().length() == 0 
            && BPCSBRTA.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '3';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() == 0 
            && BPCSBRTA.CCY.trim().length() == 0 
            && BPCSBRTA.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '4';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() > 0 
            && BPCSBRTA.CCY.trim().length() > 0 
            && BPCSBRTA.TENOR.trim().length() == 0) {
            BPCRHITB.COND = '5';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() == 0 
            && BPCSBRTA.CCY.trim().length() > 0 
            && BPCSBRTA.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '6';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() > 0 
            && BPCSBRTA.CCY.trim().length() == 0 
            && BPCSBRTA.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '7';
        }
        if (BPCSBRTA.BASE_TYP.trim().length() > 0 
            && BPCSBRTA.CCY.trim().length() > 0 
            && BPCSBRTA.TENOR.trim().length() > 0) {
            BPCRHITB.COND = '8';
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, BPCOBRTA);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 54;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        if (BPCSBRTA.START_DT >= SCCGWA.COMM_AREA.AC_DATE) {
            B050_BROWSE_BPTINTR();
            if (pgmRtn) return;
            B040_BROWSE_BPTRTFW();
            if (pgmRtn) return;
        } else {
            B030_BROWSE_BPTIHIT();
            if (pgmRtn) return;
            B040_BROWSE_BPTRTFW();
            if (pgmRtn) return;
        }
    }
    public void B030_BROWSE_BPTIHIT() throws IOException,SQLException,Exception {
        WS_FILE_NAME = 'I';
        BPCRHITB.FUNC = 'S';
        BPCRHITB.STARTD = BPCSBRTA.START_DT;
        BPCRHITB.ENDD = BPCSBRTA.END_DT;
        BPCRHITB.BR = BPCSBRTA.BR;
        BPCRHITB.CCY = BPCSBRTA.CCY;
        BPCRHITB.BASE_TYP = BPCSBRTA.BASE_TYP;
        BPCRHITB.TENOR = BPCSBRTA.TENOR;
        CEP.TRC(SCCGWA, BPCRHITB.STARTD);
        CEP.TRC(SCCGWA, BPCRHITB.ENDD);
        CEP.TRC(SCCGWA, BPCRHITB.BR);
        CEP.TRC(SCCGWA, BPCRHITB.CCY);
        CEP.TRC(SCCGWA, BPCRHITB.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITB.TENOR);
        CEP.TRC(SCCGWA, BPCRHITB);
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        while (BPCRHITB.RC.RC_CODE == 0 
            && WS_EOF_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                B300_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
        }
        BPCRHITB.FUNC = 'E';
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_BPTRTFW() throws IOException,SQLException,Exception {
        WS_FILE_NAME = 'R';
        IBS.init(SCCGWA, BPRRTFW);
        IBS.init(SCCGWA, BPCRRTFW);
        BPRRTFW.KEY.BR = BPCSBRTA.BR;
        BPCRRTFW.DATE.STARTD = BPCSBRTA.START_DT;
        BPCRRTFW.DATE.ENDD = BPCSBRTA.END_DT;
        if (BPCSBRTA.BASE_TYP.trim().length() == 0) {
            BPRRTFW.KEY.BASE_TYP = "%%%";
        } else {
            BPRRTFW.KEY.BASE_TYP = BPCSBRTA.BASE_TYP;
        }
        BPRRTFW.KEY.TENOR = BPCSBRTA.TENOR;
        if (BPCSBRTA.CCY.trim().length() == 0) {
            BPRRTFW.KEY.CCY = "%%%";
        } else {
            BPRRTFW.KEY.CCY = BPCSBRTA.CCY;
        }
        CEP.TRC(SCCGWA, BPRRTFW.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRRTFW.KEY.CCY);
        BPCRRTFW.INFO.FUNC = 'B';
        if (BPCSBRTA.TENOR.equalsIgnoreCase("0")) {
            BPCRRTFW.INFO.OPT = 'A';
        } else {
            BPCRRTFW.INFO.OPT = 'B';
        }
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        CEP.TRC(SCCGWA, BPCRRTFW.RETURN_INFO);
        CEP.TRC(SCCGWA, "RTFW-NEXT-1");
        BPCRRTFW.INFO.OPT = 'N';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        while (BPCRRTFW.RETURN_INFO != 'N' 
            && WS_EOF_FLG != 'Y') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "RTFW-NEXT-2");
            BPCRRTFW.INFO.OPT = 'N';
            S000_CALL_BPZRRTFW();
            if (pgmRtn) return;
        }
        BPCRRTFW.INFO.OPT = 'E';
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_BPTINTR() throws IOException,SQLException,Exception {
        WS_FILE_NAME = 'N';
        IBS.init(SCCGWA, BPCRINTB);
        BPCRINTB.BR = BPCSBRTA.BR;
        BPCRINTB.BASE_TYP = BPCSBRTA.BASE_TYP;
        BPCRINTB.TENOR = BPCSBRTA.TENOR;
        BPCRINTB.CCY = BPCSBRTA.CCY;
        BPCRINTB.FUNC = 'S';
        if (BPCSBRTA.TENOR.trim().length() == 0 
            || BPCSBRTA.CCY.trim().length() == 0 
            || BPCSBRTA.BASE_TYP.trim().length() == 0) {
            if (BPCSBRTA.TENOR.trim().length() == 0 
                && BPCSBRTA.CCY.trim().length() > 0 
                && BPCSBRTA.BASE_TYP.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY-BASETYP");
                BPCRINTB.COND = '5';
            }
            if (BPCSBRTA.TENOR.trim().length() > 0 
                && BPCSBRTA.BASE_TYP.trim().length() > 0 
                && BPCSBRTA.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP-TENOR");
                BPCRINTB.COND = '7';
            }
            if (BPCSBRTA.TENOR.trim().length() > 0 
                && BPCSBRTA.CCY.trim().length() > 0 
                && BPCSBRTA.BASE_TYP.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY-TENOR    ");
                BPCRINTB.COND = '6';
            }
            if (BPCSBRTA.TENOR.trim().length() == 0 
                && BPCSBRTA.CCY.trim().length() == 0 
                && BPCSBRTA.BASE_TYP.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-BASETYP");
                BPCRINTB.COND = '3';
            }
            if (BPCSBRTA.TENOR.trim().length() > 0 
                && BPCSBRTA.CCY.trim().length() == 0 
                && BPCSBRTA.BASE_TYP.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-TENOR");
                BPCRINTB.COND = '4';
            }
            if (BPCSBRTA.TENOR.trim().length() == 0 
                && BPCSBRTA.BASE_TYP.trim().length() == 0 
                && BPCSBRTA.CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR-CCY  ");
                BPCRINTB.COND = '2';
            }
            if (BPCSBRTA.TENOR.trim().length() == 0 
                && BPCSBRTA.BASE_TYP.trim().length() == 0 
                && BPCSBRTA.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "RINTB-C-BR      ");
                BPCRINTB.COND = '1';
            }
        } else {
            BPCRINTB.COND = '9';
        }
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
            && WS_EOF_FLG != 'Y') {
            B300_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCRINTB.FUNC = 'N';
            S000_CALL_BPZRINTB();
            if (pgmRtn) return;
        }
        BPCRINTB.FUNC = 'E';
        S000_CALL_BPZRINTB();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBRTA);
        if (WS_FILE_NAME == 'R') {
            CEP.TRC(SCCGWA, "BRW RTFW");
            BPCOBRTA.BR = BPRRTFW.KEY.BR;
            BPCOBRTA.TENOR = BPRRTFW.KEY.TENOR;
            BPCOBRTA.CCY = BPRRTFW.KEY.CCY;
            BPCOBRTA.RATE_TYPE = BPRRTFW.KEY.BASE_TYP;
            B300_01_GET_RATEID();
            if (pgmRtn) return;
            BPCOBRTA.RATE_ID = BPCOQRTD.DATA.RATE_ID;
            BPCOBRTA.EFF_DT = BPRRTFW.KEY.VAL_DT;
            BPCOBRTA.RATE = BPRRTFW.RATE;
            BPCOBRTA.LST_TLR = BPRRTFW.TELLER;
            WS_REC_CNT += 1;
        }
        if (WS_FILE_NAME == 'I') {
            CEP.TRC(SCCGWA, "BRW IHIT");
            BPCOBRTA.BR = BPCRHITB.BR;
            BPCOBRTA.CCY = BPCRHITB.CCY;
            BPCOBRTA.RATE_TYPE = BPCRHITB.BASE_TYP;
            BPCOBRTA.TENOR = BPCRHITB.TENOR;
            B300_01_GET_RATEID();
            if (pgmRtn) return;
            BPCOBRTA.RATE_ID = BPCOQRTD.DATA.RATE_ID;
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
            BPCOBRTA.EFF_DT = BPCRHITB.DT;
            CEP.TRC(SCCGWA, BPCRHITB.DT);
            BPCOBRTA.RATE = BPCRHITB.RATE;
            CEP.TRC(SCCGWA, BPCRHITB.RATE);
            BPCOBRTA.LST_TLR = BPCRHITB.TELLER;
            CEP.TRC(SCCGWA, BPCRHITB.TELLER);
            WS_REC_CNT += 1;
        }
        if (WS_FILE_NAME == 'N') {
            BPCOBRTA.BR = BPCRINTB.BR;
            BPCOBRTA.CCY = BPCRINTB.CCY;
            BPCOBRTA.RATE_TYPE = BPCRINTB.BASE_TYP;
            BPCOBRTA.TENOR = BPCRINTB.TENOR;
            B300_01_GET_RATEID();
            if (pgmRtn) return;
            BPCOBRTA.RATE_ID = BPCOQRTD.DATA.RATE_ID;
            CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
            BPCOBRTA.EFF_DT = BPCRINTB.DT;
            CEP.TRC(SCCGWA, BPCRINTB.DT);
            BPCOBRTA.RATE = BPCRINTB.RATE;
            CEP.TRC(SCCGWA, BPCRINTB.RATE);
            BPCOBRTA.LST_TLR = BPCRINTB.TELLER;
            CEP.TRC(SCCGWA, BPCRINTB.TELLER);
            WS_REC_CNT += 1;
        }
        CEP.TRC(SCCGWA, WS_REC_CNT);
        if (WS_REC_CNT > 5000) {
            WS_EOF_FLG = 'Y';
            WS_ERR_MSG = SCCCTLM_MSG.SC_ERR_ROW_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, BPCOBRTA);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBRTA);
        SCCMPAG.DATA_LEN = 54;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_01_GET_RATEID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQRTD);
        BPCOQRTD.DATA.CCY = BPCOBRTA.CCY;
        BPCOQRTD.DATA.BASE_TYP = BPCOBRTA.RATE_TYPE;
        BPCOQRTD.DATA.TENOR = BPCOBRTA.TENOR;
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.CCY);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.BASE_TYP);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.TENOR);
        BPCOQRTD.INQ_FLG = 'C';
        S000_CALL_BPZPQRTD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-BRW", BPCRHITB);
    }
    public void S000_CALL_BPZRRTFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTFW-MAINT", BPCRRTFW);
    }
    public void S000_CALL_BPZRINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-BRW", BPCRINTB);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
