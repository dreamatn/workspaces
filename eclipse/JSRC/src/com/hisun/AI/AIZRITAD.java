package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRITAD {
    DBParm AITITAD_RD;
    int JIBS_tmp_int;
    brParm AITITAD_BR = new brParm();
    boolean pgmRtn = false;
    AIZRITAD_WS_ITAD_DATA WS_ITAD_DATA = new AIZRITAD_WS_ITAD_DATA();
    String WS_ERR_MSG = " ";
    AIZRITAD_WS_RTN_TERM WS_RTN_TERM = new AIZRITAD_WS_RTN_TERM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRITUS AIRITUS = new AIRITUS();
    AIRITAD AIRITAD = new AIRITAD();
    AIRITAD AIRBITAD = new AIRITAD();
    AIRITM AIRITM = new AIRITM();
    SCCGWA SCCGWA;
    AICRITAD AICRITAD;
    AIRITAD AIRLITAD;
    String WS_FUNC = " ";
    public void MP(SCCGWA SCCGWA, AICRITAD AICRITAD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRITAD = AICRITAD;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "CC11");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRITAD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLITAD = (AIRITAD) AICRITAD.REC_PTR;
        AICRITAD.RETURN_INFO = 'F';
        IBS.init(SCCGWA, WS_RTN_TERM);
        IBS.init(SCCGWA, AIRITAD);
        CEP.TRC(SCCGWA, "CC12");
        IBS.CLONE(SCCGWA, AIRLITAD, AIRITAD);
        AICRITAD.RC.RC_MMO = "AI";
        AICRITAD.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "CC13");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRITAD.FUNC);
        if (AICRITAD.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICRITAD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, AIRITAD, AIRLITAD);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRITAD.OPT == 'O') {
            B060_30_STBR_BY_OLD_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (AICRITAD.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + AICRITAD.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_30_STBR_BY_OLD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_OLD_REC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LDGREAD");
        CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
        CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        IBS.READ(SCCGWA, AIRITAD, AITITAD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.REC_NOTFND, AICRITAD.RC);
            AICRITAD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        AIRITAD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRITAD.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "LDGADD");
        CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
        CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        AITITAD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRITAD, AITITAD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_NOT_DUP, AICRITAD.RC);
            AICRITAD.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        AIRITAD.DATA = "" + 0;
        JIBS_tmp_int = AIRITAD.DATA.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) AIRITAD.DATA = "0" + AIRITAD.DATA;
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        AITITAD_RD.upd = true;
        IBS.READ(SCCGWA, AIRITAD, AITITAD_RD);
        CEP.TRC(SCCGWA, "CC77");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRITAD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "REWRITE");
        CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
        CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        IBS.REWRITE(SCCGWA, AIRITAD, AITITAD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LDGDEL1");
        CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
        CEP.TRC(SCCGWA, "LDGDEL2");
        CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        IBS.DELETE(SCCGWA, AIRITAD, AITITAD_RD);
        CEP.TRC(SCCGWA, "LDGDEL3");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRITAD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_OLD_REC() throws IOException,SQLException,Exception {
        AITITAD_BR.rp = new DBParm();
        AITITAD_BR.rp.TableName = "AITITAD";
        AITITAD_BR.rp.where = "ITM_NO = :AIRITAD.KEY.ITM_NO "
            + "AND COA_FLG = :AIRITAD.KEY.COA_FLG "
            + "AND FUNC_FLG = :AIRITAD.FUNC_FLG";
        IBS.STARTBR(SCCGWA, AIRITAD, this, AITITAD_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRITAD, this, AITITAD_BR);
        CEP.TRC(SCCGWA, "OUTPUT DATA:");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, 0+"", AICRITAD.RC);
            AICRITAD.RETURN_INFO = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRITAD.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, AIRITAD.DATA, WS_ITAD_DATA);
            WS_RTN_TERM.WS_ITM_NEW = WS_ITAD_DATA.WS_ITM_NEW1;
            WS_RTN_TERM.WS_PRC_STS = WS_ITAD_DATA.WS_PRC_STS1;
        } else {
            AICRITAD.RETURN_INFO = 'O';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITITAD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRITAD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRITAD = ");
            CEP.TRC(SCCGWA, AICRITAD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
