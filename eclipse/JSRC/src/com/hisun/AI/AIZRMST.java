package com.hisun.AI;

import com.hisun.DD.DDRMST;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRMST {
    brParm AITMST_BR = new brParm();
    int WS_BR = 0;
    String TBL_NAME = "AITMST";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIB8540_AWA_8540 AIB8540_AWA_8540 = new AIB8540_AWA_8540();
    AIRMST AIRMST = new AIRMST();
    int WS_S_BR = 0;
    int WS_E_BR = 0;
    SCCGWA SCCGWA;
    AICRMST AICRMST;
    AIRMST AIRLMST;
    public void MP(SCCGWA SCCGWA, AICRMST AICRMST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRMST = AICRMST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZRMST return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMST.RC);
        CEP.TRC(SCCGWA, "11");
        AIRLMST = (DDRMST) AICRMST.INFO.REC_PTR;
        CEP.TRC(SCCGWA, "22");
        IBS.init(SCCGWA, AIRMST);
        AICRMST.INFO.REC_LEN = 802;
        CEP.TRC(SCCGWA, AICRMST.INFO.REC_LEN);
        IBS.CLONE(SCCGWA, AIRLMST, AIRMST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_S_BR = AICRMST.INFO.START_BR;
        WS_E_BR = AICRMST.INFO.END_BR;
        CEP.TRC(SCCGWA, WS_S_BR);
        CEP.TRC(SCCGWA, WS_E_BR);
        if (AICRMST.INFO.FUNC == 'B') {
            B010_BROWSE_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICRMST.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, AIRMST, AIRLMST);
    }
    public void B010_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRMST.INFO.OPT == 'S') {
            B010_10_START_BROWSE_PROC();
        } else if (AICRMST.INFO.OPT == 'K') {
            B010_20_START_BY_KEY_PROC();
        } else if (AICRMST.INFO.OPT == 'H') {
            B010_30_START_FOR_HCKD_PROC();
        } else if (AICRMST.INFO.OPT == 'V') {
            B010_40_START_FOR_VCKD_PROC();
        } else if (AICRMST.INFO.OPT == 'B') {
            B010_50_START_BY_BRCCY_PROC();
        } else if (AICRMST.INFO.OPT == 'N') {
            B010_70_READ_NEXT_PROC();
        } else if (AICRMST.INFO.OPT == 'E') {
            B010_90_END_BROWSE_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + AICRMST.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        B020_RETURN_STARTBR_INFO();
    }
    public void B010_20_START_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STBR_BY_KEY_PROC();
        B020_RETURN_STARTBR_INFO();
    }
    public void B010_30_START_FOR_HCKD_PROC() throws IOException,SQLException,Exception {
        T000_STBR_FOR_HCKD_PROC();
        B020_RETURN_STARTBR_INFO();
    }
    public void B010_40_START_FOR_VCKD_PROC() throws IOException,SQLException,Exception {
        T000_STBR_FOR_VCKD_PROC();
        B020_RETURN_STARTBR_INFO();
    }
    public void B010_50_START_BY_BRCCY_PROC() throws IOException,SQLException,Exception {
        T000_STBR_BY_BRCCY_PROC();
        B020_RETURN_STARTBR_INFO();
    }
    public void B010_70_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        if (AICRMST.INFO.REQID == '1') {
            T000_READNEXT_PROC();
        } else if (AICRMST.INFO.REQID == '2') {
            T000_READNEXT_PROC_1();
        } else if (AICRMST.INFO.REQID == '3') {
            T000_READNEXT_PROC_2();
        } else {
            T000_READNEXT_PROC();
        }
        B020_RETURN_READNEXT_INFO();
    }
    public void B010_90_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (AICRMST.INFO.REQID == '1') {
            T000_ENDBR_PROC();
        } else if (AICRMST.INFO.REQID == '2') {
            T000_ENDBR_PROC_1();
        } else if (AICRMST.INFO.REQID == '3') {
            T000_ENDBR_PROC_2();
        } else {
            T000_ENDBR_PROC();
        }
    }
    public void B020_RETURN_READNEXT_INFO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMST.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_NORMAL, AICRMST.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.REC_NOTFND, AICRMST.RC);
            AICRMST.RETURN_INFO = 'E';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B020_RETURN_STARTBR_INFO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMST.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_NORMAL, AICRMST.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.REC_NOTFND, AICRMST.RC);
            AICRMST.RETURN_INFO = 'E';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        AITMST_BR.rp = new DBParm();
        AITMST_BR.rp.TableName = "AITMST";
        IBS.STARTBR(SCCGWA, AIRMST, AITMST_BR);
    }
    public void T000_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        WS_BR = AIRMST.KEY.BR;
        if (WS_BR != 0) {
            AITMST_BR.rp = new DBParm();
            AITMST_BR.rp.TableName = "AITMST";
            AITMST_BR.rp.where = "GL_BOOK_FLG = :AIRMST.KEY.GL_BOOK_FLG "
                + "AND CCY = :AIRMST.KEY.CCY "
                + "AND BR >= :WS_S_BR "
                + "AND BR <= :WS_E_BR "
                + "AND ITM_NO >= :AIRMST.KEY.ITM_NO";
            AITMST_BR.rp.order = "GL_BOOK_FLG, CCY, BR , ITM_NO";
            IBS.STARTBR(SCCGWA, AIRMST, this, AITMST_BR);
        } else {
            AITMST_BR.rp = new DBParm();
            AITMST_BR.rp.TableName = "AITMST";
            AITMST_BR.rp.where = "GL_BOOK_FLG = :AIRMST.KEY.GL_BOOK_FLG "
                + "AND CCY = :AIRMST.KEY.CCY "
                + "AND ITM_NO >= :AIRMST.KEY.ITM_NO";
            AITMST_BR.rp.order = "GL_BOOK_FLG, CCY, ITM_NO";
            IBS.STARTBR(SCCGWA, AIRMST, this, AITMST_BR);
        }
    }
    public void T000_STBR_FOR_HCKD_PROC() throws IOException,SQLException,Exception {
        AITMST_BR.rp = new DBParm();
        AITMST_BR.rp.TableName = "AITMST";
        AITMST_BR.rp.where = "( CDCBAL _ CDDBAL _ LDCBAL + LDDBAL _ CDCRAMT + CDDRAMT ) < > 0";
        IBS.STARTBR(SCCGWA, AIRMST, this, AITMST_BR);
    }
    public void T000_STBR_FOR_VCKD_PROC() throws IOException,SQLException,Exception {
        AITMST_BR.rp = new DBParm();
        AITMST_BR.rp.TableName = "AITMST";
        AITMST_BR.rp.grp = "BR , CCY";
        AITMST_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, AIRMST, AITMST_BR);
    }
    public void T000_STBR_BY_BRCCY_PROC() throws IOException,SQLException,Exception {
        AITMST_BR.rp = new DBParm();
        AITMST_BR.rp.TableName = "AITMST";
        AITMST_BR.rp.where = "CCY = :AIRMST.KEY.CCY "
            + "AND BR = :AIRMST.KEY.BR";
        AITMST_BR.rp.Reqid = 2;
        AITMST_BR.rp.order = "BR, CCY";
        IBS.STARTBR(SCCGWA, AIRMST, this, AITMST_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMST, this, AITMST_BR);
    }
    public void T000_READNEXT_PROC_1() throws IOException,SQLException,Exception {
        AITMST_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, AIRMST, this, AITMST_BR);
    }
    public void T000_READNEXT_PROC_2() throws IOException,SQLException,Exception {
        AITMST_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, AIRMST, this, AITMST_BR);
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMST_BR);
    }
    public void T000_ENDBR_PROC_1() throws IOException,SQLException,Exception {
        AITMST_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, AITMST_BR);
    }
    public void T000_ENDBR_PROC_2() throws IOException,SQLException,Exception {
        AITMST_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, AITMST_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRMST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRMST = ");
            CEP.TRC(SCCGWA, AICRMST);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
