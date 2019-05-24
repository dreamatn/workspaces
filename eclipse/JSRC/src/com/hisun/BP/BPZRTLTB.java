package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTLTB {
    brParm BPTTLT_BR = new brParm();
    int JIBS_tmp_int;
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTLTB";
    String K_TBL_TXIF = "BPTTXIF ";
    String K_TBL_TLT = "BPTTLT  ";
    int WS_COUNT = 0;
    int WS_MIN_BR = 0;
    int WS_MAX_BR = 0;
    String WS_MIN_TLR = " ";
    String WS_MAX_TLR = " ";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCRTLTB BPCRTLTB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLL;
    public void MP(SCCGWA SCCGWA, BPCRTLTB BPCRTLTB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTLTB = BPCRTLTB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTLTB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLL = (BPRTLT) BPCRTLTB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTLT);
        IBS.CLONE(SCCGWA, BPRTLL, BPRTLT);
        BPCRTLTB.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, BPRTLT);
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTLTB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'B') {
            B015_STARTBR_BR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'U') {
            B040_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'Q') {
            B060_STARTBR_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'G') {
            B070_GROUP_BR_REC_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'C') {
            B080_GROUP_BR_NOT_AUTH();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'I') {
            B100_STARTBR_IN_BR_PROC();
            if (pgmRtn) return;
        } else if (BPCRTLTB.INFO.FUNC == 'T') {
            B110_STARTBR_BR_TLR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTLTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLT, BPRTLL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLT();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_BR_REC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLT_BR();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLT_UPD();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTLT();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTLT();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_INQ_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_INQ();
        if (pgmRtn) return;
    }
    public void B070_GROUP_BR_REC_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_BPTTLT_BR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
        if (BPRTLT.TLR_TYP != 'S') {
            BPCRTLTB.INFO.CNT = (short) WS_COUNT;
        }
    }
    public void B080_GROUP_BR_NOT_AUTH() throws IOException,SQLException,Exception {
        T000_GROUP_NOT_AUTH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
        if (BPRTLT.TLR_TYP != 'S') {
            BPCRTLTB.INFO.CNT = (short) WS_COUNT;
        }
    }
    public void B100_STARTBR_IN_BR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLT_INBR();
        if (pgmRtn) return;
    }
    public void B110_STARTBR_BR_TLR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTLT_BR_TLR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "NEW_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = :BPRTLT.SIGN_STS";
        BPTTLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLT_BR() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR";
        BPTTLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLT_INBR() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "NEW_BR = :BPRTLT.NEW_BR";
        BPTTLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLT_BR_TLR() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR "
            + "AND TLR = :BPRTLT.KEY.TLR";
        BPTTLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTLT_UPD() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.upd = true;
        BPTTLT_BR.rp.where = "NEW_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = :BPRTLT.SIGN_STS";
        BPTTLT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_INQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
        CEP.TRC(SCCGWA, BPRTLT.TLR_LVL);
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        if (BPRTLT.TLR_BR != 0) {
            BPTTLT_BR.rp = new DBParm();
            BPTTLT_BR.rp.TableName = "BPTTLT";
            BPTTLT_BR.rp.where = "TLR_BR = :BPRTLT.TLR_BR "
                + "AND TLR LIKE :BPRTLT.KEY.TLR "
                + "AND TLR_CN_NM LIKE :BPRTLT.TLR_CN_NM "
                + "AND TLR_TYP LIKE :BPRTLT.TLR_TYP "
                + "AND TLR_LVL LIKE :BPRTLT.TLR_LVL "
                + "AND TLR_STS LIKE :BPRTLT.TLR_STS";
            BPTTLT_BR.rp.errhdl = true;
            BPTTLT_BR.rp.order = "TLR";
            IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                BPCRTLTB.RETURN_INFO = 'F';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPCRTLTB.RETURN_INFO = 'N';
            } else {
            }
        } else {
            if (BPRTLT.TLR_BR == 0) {
                WS_MIN_BR = 0;
                WS_MAX_BR = 999999;
            }
            if (BPRTLT.KEY.TLR.trim().length() == 0) {
                WS_MIN_TLR = "" + 0X00;
                JIBS_tmp_int = WS_MIN_TLR.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) WS_MIN_TLR = "0" + WS_MIN_TLR;
                WS_MAX_TLR = "" + 0XFF;
                JIBS_tmp_int = WS_MAX_TLR.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) WS_MAX_TLR = "0" + WS_MAX_TLR;
            }
            BPTTLT_BR.rp = new DBParm();
            BPTTLT_BR.rp.TableName = "BPTTLT";
            BPTTLT_BR.rp.where = "( TLR_BR BETWEEN :WS_MIN_BR "
                + "AND :WS_MAX_BR ) "
                + "AND ( TLR BETWEEN :WS_MIN_TLR "
                + "AND :WS_MAX_TLR ) "
                + "AND TLR_CN_NM LIKE :BPRTLT.TLR_CN_NM "
                + "AND TLR_TYP LIKE :BPRTLT.TLR_TYP "
                + "AND TLR_LVL LIKE :BPRTLT.TLR_LVL "
                + "AND TLR_STS LIKE :BPRTLT.TLR_STS";
            BPTTLT_BR.rp.errhdl = true;
            BPTTLT_BR.rp.order = "TLR";
            IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                BPCRTLTB.RETURN_INFO = 'F';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPCRTLTB.RETURN_INFO = 'N';
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
                B_DB_EXCP();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READNEXT_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTLTB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTLTB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTLT_BR);
    }
    public void T000_REWRITE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.REWRITE(SCCGWA, BPRTLT, BPTTLT_RD);
    }
    public void T000_GROUP_BPTTLT_BR() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.set = "WS-COUNT=COUNT(*)";
        BPTTLT_RD.where = "NEW_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = :BPRTLT.SIGN_STS "
            + "AND TLR_TYP < > 'S'";
        IBS.GROUP(SCCGWA, BPRTLT, this, BPTTLT_RD);
    }
    public void T000_GROUP_NOT_AUTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK");
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.set = "WS-COUNT=COUNT(*)";
        BPTTLT_RD.where = "NEW_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = :BPRTLT.SIGN_STS "
            + "AND SIGN_TRM < > ' ' "
            + "AND TLR_TYP < > 'S'";
        IBS.GROUP(SCCGWA, BPRTLT, this, BPTTLT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTLTB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTLTB = ");
            CEP.TRC(SCCGWA, BPCRTLTB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
