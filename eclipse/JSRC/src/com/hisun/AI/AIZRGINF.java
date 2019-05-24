package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRGINF {
    DBParm AITGINF_RD;
    brParm AITGINF_BR = new brParm();
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String TBL_AITGINF = "AITGINF";
    int WS_RCD_SEQ = 0;
    AIZRGINF_WS_RVS_NO WS_RVS_NO = new AIZRGINF_WS_RVS_NO();
    int WS_DB_EXP_STDT = 0;
    int WS_DB_EXP_ENDT = 0;
    int WS_DB_SUS_STDT = 0;
    int WS_DB_SUS_ENDT = 0;
    double WS_DB_STR_AMT = 0;
    double WS_DB_END_AMT = 0;
    char WS_SUCCESS_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    AIRGINF AIRGINF = new AIRGINF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICRGINF AICRGINF;
    AIRGINF AIRLGINF;
    public void MP(SCCGWA SCCGWA, AICRGINF AICRGINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRGINF = AICRGINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRGINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "222");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICRGINF.RC);
        AIRLGINF = (AIRGINF) AICRGINF.INFO.POINTER;
        WS_DB_EXP_STDT = AICRGINF.INFO.EXP_STDT;
        CEP.TRC(SCCGWA, "333");
        WS_DB_EXP_ENDT = AICRGINF.INFO.EXP_ENDT;
        CEP.TRC(SCCGWA, "444");
        IBS.CLONE(SCCGWA, AIRLGINF, AIRGINF);
        CEP.TRC(SCCGWA, "555");
        CEP.TRC(SCCGWA, AIRGINF);
        CEP.TRC(SCCGWA, AICRGINF);
        CEP.TRC(SCCGWA, "666");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRGINF.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRGINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRGINF, AIRLGINF);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        while (WS_SUCCESS_FLG != 'Y') {
            T000_WRITE_AITGINF();
            if (pgmRtn) return;
            if (WS_SUCCESS_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, AIRGINF.KEY.RVS_NO, WS_RVS_NO);
                WS_RVS_NO.WS_SEQ += 1;
                AIRGINF.KEY.RVS_NO = IBS.CLS2CPY(SCCGWA, WS_RVS_NO);
                CEP.TRC(SCCGWA, "DKDD");
                CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                CEP.TRC(SCCGWA, WS_RVS_NO.WS_SEQ);
            }
        }
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITGINF_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_AITGINF();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_AITGINF();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_AITGINF();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRGINF.INFO.OPT == 'F') {
            T000_STARTBR_AITGINF_FIRST();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.OPT == 'L') {
            T000_STARTBR_AITGINF_FIRST_2();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.OPT == '6') {
            T000_STARTBR_AITGINF_AC();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.OPT == 'N') {
            T000_READNEXT_AITGINF();
            if (pgmRtn) return;
        } else if (AICRGINF.INFO.OPT == 'E') {
            T000_ENDBR_AITGINF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRGINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.errhdl = true;
        IBS.READ(SCCGWA, AIRGINF, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGINF.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITGINF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRGINF, AITGINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGINF.RETURN_INFO = 'F';
            WS_SUCCESS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRGINF.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_GINF_ALREDY_EXIST, AICRGINF.RC);
            WS_SUCCESS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITGINF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITGINF_UPD() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.upd = true;
        IBS.READ(SCCGWA, AIRGINF, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGINF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITGINF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        IBS.REWRITE(SCCGWA, AIRGINF, AITGINF_RD);
    }
    public void T000_DELETE_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        IBS.DELETE(SCCGWA, AIRGINF, AITGINF_RD);
    }
    public void T000_STARTBR_AITGINF_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR AIRGINF BY MIB AC");
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "AC = :AIRGINF.AC";
        AITGINF_BR.rp.order = "RVS_NO DESC";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGINF.RETURN_INFO = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
        }
    }
    public void T000_STARTBR_AITGINF_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGINF.AC);
        CEP.TRC(SCCGWA, AIRGINF.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRGINF.CCY);
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.where = "AC = :AIRGINF.AC "
            + "AND BOOK_FLG = :AIRGINF.BOOK_FLG "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS = 'N'";
        AITGINF_RD.fst = true;
        AITGINF_RD.order = "RVS_NO DESC";
        IBS.READ(SCCGWA, AIRGINF, this, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            AICRGINF.RETURN_INFO = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
        }
    }
    public void T000_STARTBR_AITGINF_FIRST_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        if (AIRGINF.KEY.RVS_NO == null) AIRGINF.KEY.RVS_NO = "";
        JIBS_tmp_int = AIRGINF.KEY.RVS_NO.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) AIRGINF.KEY.RVS_NO += " ";
        AIRGINF.KEY.RVS_NO = AIRGINF.KEY.RVS_NO.substring(0, 16 - 1) + "%%%%%%" + AIRGINF.KEY.RVS_NO.substring(16 + 6 - 1);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.where = "RVS_NO LIKE :AIRGINF.KEY.RVS_NO";
        AITGINF_RD.fst = true;
        AITGINF_RD.order = "RVS_NO DESC";
        IBS.READ(SCCGWA, AIRGINF, this, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            AICRGINF.RETURN_INFO = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
        }
    }
    public void T000_READNEXT_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRGINF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRGINF.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITGINF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITGINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGINF_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRGINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
