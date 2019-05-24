package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSCVBR {
    String JIBS_tmp_str[] = new String[10];
    brParm AITMIB_BR = new brParm();
    DBParm AITONB_RD;
    boolean pgmRtn = false;
    String TBL_AITONB = "AITONB ";
    short K_MAX_DD = 40;
    String WS_ERR_MSG = " ";
    int WS_VAL_DATE = 0;
    int WS_NEW_BR = 0;
    char WS_NEW_HOT_FLG = ' ';
    char WS_OLD_HOT_FLG = ' ';
    AIZSCVBR_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSCVBR_WS_BRW_OUTPUT();
    int WS_AC_BR = 0;
    String WS_AC_NO = " ";
    char WS_FIND_MIB_FLG = ' ';
    char WS_FIND_ONB_FLG = ' ';
    char WS_EOF_MIB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICRCVBR AICRCVBR = new AICRCVBR();
    AIRONB AIRONB = new AIRONB();
    AIRMIB AIRMIB = new AIRMIB();
    AIRMIB AIRWMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRCMIB AIRGCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICRMIB AICRMIB = new AICRMIB();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    SCCGWA SCCGWA;
    AICSCVBR AICSCVBR;
    public void MP(SCCGWA SCCGWA, AICSCVBR AICSCVBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSCVBR = AICSCVBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSCVBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICSCVBR.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCVBR.FUNC == 'Q') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCVBR.FUNC == 'A') {
            B020_CHECK_INPUT();
            if (pgmRtn) return;
            B030_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCVBR.FUNC == 'M') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCVBR.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCVBR.FUNC == 'K') {
            B060_CHECK_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!AICSCVBR.OITM.equalsIgnoreCase(AICSCVBR.ITM)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9436;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!AICSCVBR.OCCY.equalsIgnoreCase(AICSCVBR.CCY)) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.M_CHK_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.ORGI_FLG = '0';
        BPCUCHBR.OLD_BR = AICSCVBR.OBR;
        S000_CALL_BPZUCHBR();
        if (pgmRtn) return;
        if (BPCUCHBR.NEW_BR != AICSCVBR.BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHEB_ERROR3;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = 'F';
        AIRMIB.KEY.GL_BOOK = "BK001";
        AIRMIB.KEY.BR = AICSCVBR.OBR;
        AIRMIB.KEY.ITM_NO = AICSCVBR.OITM;
        AIRMIB.KEY.SEQ = AICSCVBR.OSEQ;
        AIRMIB.KEY.CCY = AICSCVBR.OCCY;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_OLD_HOT_FLG = AIRMIB.HOT_FLG;
            IBS.CLONE(SCCGWA, AIRMIB, AIRWMIB);
        }
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = 'F';
        AIRMIB.KEY.GL_BOOK = "BK001";
        AIRMIB.KEY.BR = AICSCVBR.BR;
        AIRMIB.KEY.ITM_NO = AICSCVBR.ITM;
        AIRMIB.KEY.SEQ = AICSCVBR.SEQ;
        AIRMIB.KEY.CCY = AICSCVBR.CCY;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'N') {
            if (AICSCVBR.ITM.equalsIgnoreCase("22411011")) {
                IBS.init(SCCGWA, AIRMIB);
                AICRMIB.FUNC = 'C';
                IBS.CLONE(SCCGWA, AIRWMIB, AIRMIB);
                AIRMIB.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AIRMIB.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
                AIRMIB.KEY.BR = AICSCVBR.BR;
                AIRMIB.KEY.SEQ = AICSCVBR.SEQ;
                AIRMIB.AC_NO = AICSCVBR.AC_NO;
                AIRMIB.CLS_DATE = 0;
                AIRMIB.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
                AIRMIB.LBAL = 0;
                AIRMIB.CBAL = 0;
                AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AIRMIB.DRLT_BAL = 0;
                AIRMIB.CRLT_BAL = 0;
                WS_NEW_HOT_FLG = AIRMIB.HOT_FLG;
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.BR = AICSCVBR.BR;
                AIRCMIB.KEY.ITM = AICSCVBR.ITM;
                AIRCMIB.KEY.SEQ = AICSCVBR.SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_NEW_HOT_FLG = AIRCMIB.HOT_FLG;
                }
            }
        } else {
            WS_NEW_HOT_FLG = AIRMIB.HOT_FLG;
            if (AIRMIB.STS == 'C') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.IN_AC_STS_UNINVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_OLD_HOT_FLG != WS_NEW_HOT_FLG) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_HOTFLG_NOTEQ_CMIB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        if (AICPCMIB.RETURN_INFO == 'F') {
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRONB);
        IBS.init(SCCGWA, AICRCVBR);
        AICRCVBR.FUNC = 'Q';
        AIRONB.OBR = AICSCVBR.OBR;
        AICRCVBR.POINTER = AIRONB;
        AICRCVBR.REC_LEN = 122;
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
        if (AICRCVBR.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRONB);
        IBS.init(SCCGWA, AICRCVBR);
        AICRCVBR.FUNC = 'C';
        AIRONB.KEY.OAC_NO = AICSCVBR.OAC_NO;
        AIRONB.OBR = AICSCVBR.OBR;
        AIRONB.OCCY = AICSCVBR.OCCY;
        AIRONB.OITM = AICSCVBR.OITM;
        AIRONB.OSEQ = AICSCVBR.OSEQ;
        AIRONB.AC_NO = AICSCVBR.AC_NO;
        AIRONB.BR = AICSCVBR.BR;
        AIRONB.CCY = AICSCVBR.CCY;
        AIRONB.ITM = AICSCVBR.ITM;
        AIRONB.SEQ = AICSCVBR.SEQ;
        AIRONB.AC_DATE = WS_VAL_DATE;
        AICRCVBR.POINTER = AIRONB;
        AICRCVBR.REC_LEN = 122;
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
        if (AICRCVBR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICRCVBR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(AICMSG_ERROR_MSG.AI_CPRD_ALREADY_EXIST)) {
                B040_MODIFY_PROCESS();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCVBR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        if (AICRCVBR.RETURN_INFO == 'F') {
            AICRCVBR.FUNC = 'D';
            AICRCVBR.POINTER = AIRONB;
            AICRCVBR.REC_LEN = 122;
            S000_CALL_AIZRCVBR();
            if (pgmRtn) return;
            AICRCVBR.FUNC = 'C';
            AIRONB.KEY.OAC_NO = AICSCVBR.OAC_NO;
            AIRONB.OBR = AICSCVBR.OBR;
            AIRONB.OCCY = AICSCVBR.OCCY;
            AIRONB.OITM = AICSCVBR.OITM;
            AIRONB.OSEQ = AICSCVBR.OSEQ;
            AIRONB.AC_NO = AICSCVBR.AC_NO;
            AIRONB.BR = AICSCVBR.BR;
            AIRONB.CCY = AICSCVBR.CCY;
            AIRONB.ITM = AICSCVBR.ITM;
            AIRONB.SEQ = AICSCVBR.SEQ;
            AIRONB.AC_DATE = WS_VAL_DATE;
            AICRCVBR.POINTER = AIRONB;
            AICRCVBR.REC_LEN = 122;
            S000_CALL_AIZRCVBR();
            if (pgmRtn) return;
            if (AICRCVBR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCVBR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        if (AICRCVBR.RETURN_INFO == 'F') {
            AICRCVBR.FUNC = 'D';
            AICRCVBR.POINTER = AIRONB;
            AICRCVBR.REC_LEN = 122;
            S000_CALL_AIZRCVBR();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRONB);
        IBS.init(SCCGWA, AIRMIB);
        WS_AC_BR = AICSCVBR.OBR;
        CEP.TRC(SCCGWA, "POCGGL0401");
        CEP.TRC(SCCGWA, WS_AC_BR);
        T00_STARTBR_AITMIB();
        if (pgmRtn) return;
        while (WS_EOF_MIB_FLG != 'Y') {
            T00_READNEXT_AITMIB();
            if (pgmRtn) return;
            if (WS_FIND_MIB_FLG == 'Y') {
                WS_AC_NO = AIRMIB.AC_NO;
                T00_READ_AITONB();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_READUDP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRONB);
        IBS.init(SCCGWA, AICRCVBR);
        AICRCVBR.FUNC = 'R';
        AIRONB.KEY.OAC_NO = AICSCVBR.OAC_NO;
        AICRCVBR.POINTER = AIRONB;
        AICRCVBR.REC_LEN = 122;
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
        if (AICRCVBR.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 122;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICRCVBR);
        IBS.init(SCCGWA, AIRONB);
        AICRCVBR.FUNC = 'B';
        AICRCVBR.OPT = 'S';
        AIRONB.OBR = AICSCVBR.OBR;
        AICRCVBR.POINTER = AIRONB;
        AICRCVBR.REC_LEN = 122;
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
        AICRCVBR.OPT = 'N';
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
        if (AICRCVBR.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (AICRCVBR.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_TRANS_DATA_BRW_OUTPUT();
            if (pgmRtn) return;
            AICRCVBR.OPT = 'N';
            S000_CALL_AIZRCVBR();
            if (pgmRtn) return;
        }
        AICRCVBR.OPT = 'E';
        S000_CALL_AIZRCVBR();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_OUT_OAC = AIRONB.KEY.OAC_NO;
        WS_BRW_OUTPUT.WS_BRW_OUT_OBR = AIRONB.OBR;
        WS_BRW_OUTPUT.WS_BRW_OUT_OCCY = AIRONB.OCCY;
        WS_BRW_OUTPUT.WS_BRW_OUT_OITM = AIRONB.OITM;
        WS_BRW_OUTPUT.WS_BRW_OUT_OSEQ = AIRONB.OSEQ;
        WS_BRW_OUTPUT.WS_BRW_OUT_AC = AIRONB.AC_NO;
        WS_BRW_OUTPUT.WS_BRW_OUT_BR = AIRONB.BR;
        WS_BRW_OUTPUT.WS_BRW_OUT_CCY = AIRONB.CCY;
        WS_BRW_OUTPUT.WS_BRW_OUT_ITM = AIRONB.ITM;
        WS_BRW_OUTPUT.WS_BRW_OUT_SEQ = AIRONB.SEQ;
        WS_BRW_OUTPUT.WS_BRW_OUT_DATE = AIRONB.AC_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 122;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_AITMIB() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "BR = :WS_AC_BR "
            + "AND STS = 'N'";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T00_READNEXT_AITMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIB, this, AITMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FIND_MIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EOF_MIB_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_AITONB() throws IOException,SQLException,Exception {
        AITONB_RD = new DBParm();
        AITONB_RD.TableName = "AITONB";
        AITONB_RD.where = "OAC_NO = :WS_AC_NO";
        IBS.READ(SCCGWA, AIRONB, this, AITONB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FIND_ONB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EOF_MIB_FLG = 'Y';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BRCH_ACC_ERROR, AICSCVBR.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRCVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CVBR", AICRCVBR);
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
        if (BPCUCHBR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCHBR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_VAL_DATE = BPCUCHBR.INCO_DATE;
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
