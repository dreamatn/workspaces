package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;
import com.hisun.AI.*;

public class BPZPQENT {
    String JIBS_tmp_str[] = new String[10];
    brParm AITENTY_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN    ";
    BPZPQENT_WS_TEMP_BANK WS_TEMP_BANK = new BPZPQENT_WS_TEMP_BANK();
    short WS_I = 0;
    short WS_J = 0;
    short WS_LK_CNT = 0;
    BPZPQENT_WS_ENTY_KEY WS_ENTY_KEY = new BPZPQENT_WS_ENTY_KEY();
    short WS_ENTY_LAST_BEGSEQ = 0;
    char WS_REDORD_FLAG = 'N';
    char WS_MODNO_FLAG = 'N';
    char WS_EVENT_TYPE_FLAG = 'N';
    char WS_GL_BOOK_FLAG = 'N';
    char WS_ENTY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMB BPCPRMB = new BPCPRMB();
    AIRENTY AIRENTY = new AIRENTY();
    AICENTY AICMENTY = new AICENTY();
    BPCPRMBR BPCPRMBR = new BPCPRMBR();
    SCCGWA SCCGWA;
    BPCPQENT BPCPQENT;
    SCCCWA SCCCWA;
    public void MP(SCCGWA SCCGWA, BPCPQENT BPCPQENT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQENT = BPCPQENT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQENT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPQENT.RC.RC_MMO = "BP";
        BPCPQENT.RC.RC_CODE = 0;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        R000_SET_LK_MMT();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQENT.FUNC);
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.MODNO);
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.GL_BOOK);
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_TYPE);
        if (BPCPQENT.FUNC == 'Q') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCPQENT.FUNC == 'B') {
            B030_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCPQENT.FUNC == 'N') {
            B040_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCPQENT.FUNC == 'E') {
            B050_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPQENT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQENT.DATA_INFO.MODNO.trim().length() == 0 
            || BPCPQENT.DATA_INFO.EVENT_TYPE.trim().length() == 0 
            || BPCPQENT.DATA_INFO.GL_BOOK.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQENT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRENTY);
        IBS.init(SCCGWA, AICMENTY);
        AIRENTY.KEY.GL_BOOK = BPCPQENT.DATA_INFO.GL_BOOK;
        AIRENTY.KEY.MODNO = BPCPQENT.DATA_INFO.MODNO;
        AIRENTY.KEY.EVENT_TYPE = BPCPQENT.DATA_INFO.EVENT_TYPE;
        AIRENTY.KEY.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRENTY.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_ENTY_KEY.WS_GL_BOOK = BPCPQENT.DATA_INFO.GL_BOOK;
        WS_ENTY_KEY.WS_MODNO = BPCPQENT.DATA_INFO.MODNO;
        WS_ENTY_KEY.WS_EVENT_TYPE = BPCPQENT.DATA_INFO.EVENT_TYPE;
        CEP.TRC(SCCGWA, WS_ENTY_KEY);
        T000_STARTBR_AITENTY_QUERY();
        if (pgmRtn) return;
        B060_OUTPUT_INFO();
        if (pgmRtn) return;
        T000_ENDBR_AITENTY();
        if (pgmRtn) return;
    }
    public void B030_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRENTY);
        AIRENTY.KEY.GL_BOOK = BPCPQENT.DATA_INFO.GL_BOOK;
        AIRENTY.KEY.MODNO = BPCPQENT.DATA_INFO.MODNO;
        AIRENTY.KEY.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRENTY.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_AITENTY_BRW();
        if (pgmRtn) return;
    }
    public void B040_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        B060_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B050_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_AITENTY();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_INFO() throws IOException,SQLException,Exception {
        WS_REDORD_FLAG = 'Y';
        T000_READNEXT_AITENTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_TYPE);
        CEP.TRC(SCCGWA, AIRENTY.KEY.EVENT_TYPE);
        if (BPCPQENT.DATA_INFO.EVENT_TYPE.trim().length() > 0 
            && !BPCPQENT.DATA_INFO.EVENT_TYPE.equalsIgnoreCase(AIRENTY.KEY.EVENT_TYPE)) {
            WS_ENTY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_ENTY_FLG);
        if (WS_ENTY_FLG == 'Y') {
            BPCPQENT.DATA_INFO.MODNO = AIRENTY.KEY.MODNO;
            BPCPQENT.DATA_INFO.EVENT_TYPE = AIRENTY.KEY.EVENT_TYPE;
            BPCPQENT.DATA_INFO.GL_BOOK = AIRENTY.KEY.GL_BOOK;
            BPCPQENT.DATA_INFO.UPD_TEL = AIRENTY.UPD_TEL;
            BPCPQENT.DATA_INFO.UPD_DATE = AIRENTY.UPD_DATE;
            BPCPQENT.DATA_INFO.UPD_TIME = AIRENTY.UPD_TIME;
            BPCPQENT.DATA_INFO.SUP_TEL1 = AIRENTY.SUP_TEL1;
            BPCPQENT.DATA_INFO.SUP_TEL2 = AIRENTY.SUP_TEL2;
        } else {
            if (BPCPQENT.FUNC == 'Q' 
                || BPCPQENT.DATA_INFO.EVENT_TYPE.trim().length() > 0) {
                CEP.TRC(SCCGWA, "DASI");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_9380, BPCPQENT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        for (WS_I = 1; WS_ENTY_FLG != 'N' 
            && WS_REDORD_FLAG != 'N'; WS_I += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRENTY);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICMENTY);
            CEP.TRC(SCCGWA, AIRENTY);
            CEP.TRC(SCCGWA, AICMENTY);
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].ENTRY_NUMBER = AICMENTY.SORT_SEQ;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].TR_GROUP = AICMENTY.TR_GROUP;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].GL_MST = AICMENTY.GLMST_PNT;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].DR_CR = AICMENTY.DR_CR;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].GL_CODE = AICMENTY.ITM_CODE;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].AC_KEY = AICMENTY.ITM_PNT;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].BR_FLG = AICMENTY.BR_FLG;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].BR = AICMENTY.BR;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].CCY_PNT = AICMENTY.CCY_PNT;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].POST_AUTH = AICMENTY.POST_AUTH;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].RM_FLG = AICMENTY.RM_FLG;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].EFF_DAYS = AICMENTY.EFF_DAYS;
            BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].AMT_METHOD = AICMENTY.AMT_METHOD;
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO.EVENT_DATA[WS_I-1].AMT_METHOD);
            BPCPQENT.DATA_INFO.CNT += 1;
            T000_READNEXT_AITENTY();
            if (pgmRtn) return;
            if (!AIRENTY.KEY.EVENT_TYPE.equalsIgnoreCase(BPCPQENT.DATA_INFO.EVENT_TYPE)) {
                WS_REDORD_FLAG = 'N';
            }
        }
    }
    public void T000_STARTBR_AITENTY_QUERY() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.DBIO_FLG = '0';
            WS_J = 0;
            IBS.init(SCCGWA, BPCPRMBR.CD);
            WS_J = 1;
            BPCPRMBR.TYP = "AITENTY";
            BPCPRMBR.CD.KEY = IBS.CLS2CPY(SCCGWA, WS_ENTY_KEY);
            BPCPRMBR.LTH = 266;
            BPCPRMBR.CD.KEY_KEYSEQ = WS_J;
            WS_ENTY_LAST_BEGSEQ = 0;
            BPCPRMBR.CD.KEY_BEGSEQ = WS_ENTY_LAST_BEGSEQ;
            CEP.TRC(SCCGWA, BPCPRMBR.TYP);
            CEP.TRC(SCCGWA, BPCPRMBR.CD.KEY);
            CEP.TRC(SCCGWA, BPCPRMBR.CD.KEY_KEYSEQ);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        } else {
            CEP.TRC(SCCGWA, "JINSHENG");
            AITENTY_BR.rp = new DBParm();
            AITENTY_BR.rp.TableName = "AITENTY";
            AITENTY_BR.rp.where = "GL_BOOK = :AIRENTY.KEY.GL_BOOK "
                + "AND MODNO = :AIRENTY.KEY.MODNO "
                + "AND EVENT_TYPE = :AIRENTY.KEY.EVENT_TYPE "
                + "AND EFF_DT <= :AIRENTY.KEY.EFF_DT "
                + "AND EXP_DT >= :AIRENTY.KEY.EXP_DT";
            IBS.STARTBR(SCCGWA, AIRENTY, this, AITENTY_BR);
        }
    } else { //FROM #ELSE
        AITENTY_BR.rp = new DBParm();
        AITENTY_BR.rp.TableName = "AITENTY";
        AITENTY_BR.rp.where = "GL_BOOK = :AIRENTY.KEY.GL_BOOK "
            + "AND MODNO = :AIRENTY.KEY.MODNO "
            + "AND EVENT_TYPE = :AIRENTY.KEY.EVENT_TYPE "
            + "AND EFF_DT <= :AIRENTY.KEY.EFF_DT "
            + "AND EXP_DT >= :AIRENTY.KEY.EXP_DT";
        IBS.STARTBR(SCCGWA, AIRENTY, this, AITENTY_BR);
    } //FROM #ENDIF
    }
    public void T000_STARTBR_AITENTY_BRW() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.DBIO_FLG = '0';
            WS_J = 0;
            IBS.init(SCCGWA, BPCPRMBR.CD);
            WS_J = 1;
            BPCPRMBR.TYP = "AITENTY";
            BPCPRMBR.CD.KEY = IBS.CLS2CPY(SCCGWA, WS_ENTY_KEY);
            BPCPRMBR.LTH = 266;
            BPCPRMBR.CD.KEY_KEYSEQ = WS_J;
            WS_ENTY_LAST_BEGSEQ = 0;
            BPCPRMBR.CD.KEY_BEGSEQ = WS_ENTY_LAST_BEGSEQ;
        } else {
            AITENTY_BR.rp = new DBParm();
            AITENTY_BR.rp.TableName = "AITENTY";
            AITENTY_BR.rp.where = "GL_BOOK = :AIRENTY.KEY.GL_BOOK "
                + "AND MODNO = :AIRENTY.KEY.MODNO "
                + "AND EVENT_TYPE = :AIRENTY.KEY.EVENT_TYPE "
                + "AND EFF_DT <= :AIRENTY.KEY.EFF_DT "
                + "AND EXP_DT >= :AIRENTY.KEY.EXP_DT";
            IBS.STARTBR(SCCGWA, AIRENTY, this, AITENTY_BR);
        }
    } else { //FROM #ELSE
        AITENTY_BR.rp = new DBParm();
        AITENTY_BR.rp.TableName = "AITENTY";
        AITENTY_BR.rp.where = "GL_BOOK = :AIRENTY.KEY.GL_BOOK "
            + "AND MODNO = :AIRENTY.KEY.MODNO "
            + "AND EFF_DT <= :AIRENTY.KEY.EFF_DT "
            + "AND EXP_DT >= :AIRENTY.KEY.EXP_DT";
        IBS.STARTBR(SCCGWA, AIRENTY, this, AITENTY_BR);
    } //FROM #ENDIF
    }
    public void T000_READNEXT_AITENTY() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.DBIO_FLG = '0';
            WS_ENTY_FLG = 'N';
            IBS.init(SCCGWA, BPCPRMBR.CD);
            BPCPRMBR.TYP = "AITENTY";
            BPCPRMBR.CD.KEY = IBS.CLS2CPY(SCCGWA, WS_ENTY_KEY);
            BPCPRMBR.LTH = 266;
            BPCPRMBR.CD.KEY_KEYSEQ = WS_J;
            BPCPRMBR.CD.KEY_BEGSEQ = WS_ENTY_LAST_BEGSEQ;
            AIRENTY = (AIRENTY) IBS.HASHLKUP(SCCGWA, "AIRENTY", BPCPRMBR.CD);
            WS_J += 1;
            WS_ENTY_LAST_BEGSEQ = 0;
            WS_ENTY_LAST_BEGSEQ = BPCPRMBR.CD.KEY_BEGSEQ;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, AIRENTY);
        } else {
            IBS.READNEXT(SCCGWA, AIRENTY, this, AITENTY_BR);
        }
    } else { //FROM #ELSE
        IBS.READNEXT(SCCGWA, AIRENTY, this, AITENTY_BR);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ENTY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ENTY_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITENTY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITENTY() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
        } else {
            IBS.ENDBR(SCCGWA, AITENTY_BR);
        }
    }
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPCPRMBR.TYP.equalsIgnoreCase("CITDTL")
            || BPCPRMBR.TYP.equalsIgnoreCase("CITDINF")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITENTY")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITGLM")) {
            BPCPRMBR.I = 12;
        } else {
            BPCPRMBR.I = 12;
        }
        if (SCCCWA.PARM_IN_USE[BPCPRMBR.I-1] == 1) {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR1_AREA[BPCPRMBR.I-1].PARM_PTR1;
        } else {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR2_AREA[BPCPRMBR.I-1].PARM_PTR2;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQENT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQENT = ");
            CEP.TRC(SCCGWA, BPCPQENT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
