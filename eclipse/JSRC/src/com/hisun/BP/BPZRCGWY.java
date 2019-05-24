package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCGWY {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTCGWY_RD;
    brParm BPTCGWY_BR = new brParm();
    boolean pgmRtn = false;
    int WS_COUNT = 0;
    char WS_TBL_CGWY_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCGWY BPRCGWY = new BPRCGWY();
    SCCGWA SCCGWA;
    BPCRCGWY BPCRCGWY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRCGWY BPRCGWY1;
    public void MP(SCCGWA SCCGWA, BPCRCGWY BPCRCGWY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCGWY = BPCRCGWY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCGWY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCGWY1 = (BPRCGWY) BPCRCGWY.POINTER;
        CEP.TRC(SCCGWA, "00");
        CEP.TRC(SCCGWA, "11");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCGWY1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCGWY);
        CEP.TRC(SCCGWA, "22");
        BPCRCGWY.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "33");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCGWY.FUNC == 'A') {
            B010_RECORD_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'Q') {
            B020_RECORD_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'Y') {
            B020_RECORD_INQ_1_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'D') {
            B030_RECORD_DLE_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'M') {
            B040_READUPD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'U') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'B') {
            B060_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'R') {
            B070_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRCGWY.FUNC == 'E') {
            B080_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCGWY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCGWY);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCGWY, BPRCGWY1);
    }
    public void B010_RECORD_ADD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_1_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_1_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B030_RECORD_DLE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B040_READUPD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_PROC() throws IOException,SQLException,Exception {
        if (BPCRCGWY.OPTION == '1') {
            T000_STARTBR_BPRCGWY_1();
            if (pgmRtn) return;
        } else if (BPCRCGWY.OPTION == '2') {
            T000_STARTBR_BPRCGWY_2();
            if (pgmRtn) return;
        } else if (BPCRCGWY.OPTION == '3') {
            T000_STARTBR_BPRCGWY_3();
            if (pgmRtn) return;
        } else if (BPCRCGWY.OPTION == '4') {
            T000_STARTBR_BPRCGWY_4();
            if (pgmRtn) return;
        } else if (BPCRCGWY.OPTION == '5') {
            T000_STARTBR_BPRCGWY_5();
            if (pgmRtn) return;
        }
    }
    public void B070_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRCGWY();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRCGWY();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        IBS.WRITE(SCCGWA, BPRCGWY, BPTCGWY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCGWY.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        IBS.READ(SCCGWA, BPRCGWY, BPTCGWY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCGWY.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_1_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        BPTCGWY_RD.where = "PRDT_CODE = :BPRCGWY.KEY.PRDT_CODE";
        IBS.READ(SCCGWA, BPRCGWY, this, BPTCGWY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUPD_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        BPTCGWY_RD.upd = true;
        IBS.READ(SCCGWA, BPRCGWY, BPTCGWY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        IBS.REWRITE(SCCGWA, BPRCGWY, BPTCGWY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPRCGWY() throws IOException,SQLException,Exception {
        BPTCGWY_RD = new DBParm();
        BPTCGWY_RD.TableName = "BPTCGWY";
        BPTCGWY_RD.where = "PRDT_CODE = :BPRCGWY.KEY.PRDT_CODE";
        IBS.DELETE(SCCGWA, BPRCGWY, this, BPTCGWY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRCGWY_1() throws IOException,SQLException,Exception {
        BPTCGWY_BR.rp = new DBParm();
        BPTCGWY_BR.rp.TableName = "BPTCGWY";
        IBS.STARTBR(SCCGWA, BPRCGWY, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRCGWY_2() throws IOException,SQLException,Exception {
        BPTCGWY_BR.rp = new DBParm();
        BPTCGWY_BR.rp.TableName = "BPTCGWY";
        BPTCGWY_BR.rp.where = "PRDT_CODE = :BPRCGWY.KEY.PRDT_CODE";
        IBS.STARTBR(SCCGWA, BPRCGWY, this, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRCGWY_3() throws IOException,SQLException,Exception {
        BPTCGWY_BR.rp = new DBParm();
        BPTCGWY_BR.rp.TableName = "BPTCGWY";
        BPTCGWY_BR.rp.where = "CHANG_WAY = :BPRCGWY.KEY.CHANG_WAY";
        IBS.STARTBR(SCCGWA, BPRCGWY, this, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRCGWY_4() throws IOException,SQLException,Exception {
        BPTCGWY_BR.rp = new DBParm();
        BPTCGWY_BR.rp.TableName = "BPTCGWY";
        BPTCGWY_BR.rp.where = "PRDT_CODE = :BPRCGWY.KEY.PRDT_CODE "
            + "AND CHANG_WAY = :BPRCGWY.KEY.CHANG_WAY";
        IBS.STARTBR(SCCGWA, BPRCGWY, this, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPRCGWY_5() throws IOException,SQLException,Exception {
        BPTCGWY_BR.rp = new DBParm();
        BPTCGWY_BR.rp.TableName = "BPTCGWY";
        BPTCGWY_BR.rp.where = "PARM_CODE = :BPRCGWY.PARM_CODE";
        IBS.STARTBR(SCCGWA, BPRCGWY, this, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRCGWY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCGWY, this, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRCGWY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCGWY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCGWY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCGWY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCGWY.RETURN_INFO);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCGWY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCGWY = ");
            CEP.TRC(SCCGWA, BPCRCGWY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
