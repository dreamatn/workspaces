package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTFBAS {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFBAS_RD;
    brParm BPTFBAS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTFBAS";
    String K_TBL_FARM = "BPTFBAS ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFBAS BPRFBAS = new BPRFBAS();
    SCCGWA SCCGWA;
    BPCTFBAS BPCTFBAS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFBAS BPRFBAS1;
    public void MP(SCCGWA SCCGWA, BPCTFBAS BPCTFBAS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTFBAS = BPCTFBAS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTFBAS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFBAS1 = (BPRFBAS) BPCTFBAS.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.INFO.REC_LEN);
        CEP.TRC(SCCGWA, BPRFBAS1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFBAS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFBAS1, BPRFBAS);
        CEP.TRC(SCCGWA, "2222");
        CEP.TRC(SCCGWA, BPRFBAS);
        BPCTFBAS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCTFBAS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TFBAS-FUNC");
        if (BPCTFBAS.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTFBAS.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFBAS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFBAS, BPRFBAS1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-CREATE-RECORD-PROC");
        T000_WRITE_BPTFBAS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFBAS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FARM_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FARM_NOTFND, BPCTFBAS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRFBAS.FEE_NO != 0) {
            T000_READ_BPTFBAS_FEE_NO();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTFBAS();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFBAS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTFBAS();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTFBAS.INFO.OPT == 'S') {
            if (BPRFBAS.KEY.FEE_CODE.trim().length() > 0) {
                T002_STARTBR_BPTFBAS();
                if (pgmRtn) return;
            } else {
                T001_STARTBR_BPTFBAS();
                if (pgmRtn) return;
            }
        } else if (BPCTFBAS.INFO.OPT == 'N') {
            T000_READNEXT_BPTFBAS();
            if (pgmRtn) return;
        } else if (BPCTFBAS.INFO.OPT == 'E') {
            T000_ENDBR_BPTFBAS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT (" + BPCTFBAS.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        IBS.READ(SCCGWA, BPRFBAS, BPTFBAS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRFBAS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFBAS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTFBAS_FEE_NO() throws IOException,SQLException,Exception {
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        BPTFBAS_RD.eqWhere = "FEE_CODE";
        IBS.READ(SCCGWA, BPRFBAS, BPTFBAS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPRFBAS.UP_AMT);
        CEP.TRC(SCCGWA, BPRFBAS.DWN_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFBAS.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_BR.rp = new DBParm();
        BPTFBAS_BR.rp.TableName = "BPTFBAS";
        BPTFBAS_BR.rp.where = "FEE_NO = :BPRFBAS.FEE_NO";
        BPTFBAS_BR.rp.order = "FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFBAS, this, BPTFBAS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T001_STARTBR_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_BR.rp = new DBParm();
        BPTFBAS_BR.rp.TableName = "BPTFBAS";
        BPTFBAS_BR.rp.where = "FEE_CODE >= :BPRFBAS.KEY.FEE_CODE";
        BPTFBAS_BR.rp.order = "FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFBAS, this, BPTFBAS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T002_STARTBR_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_BR.rp = new DBParm();
        BPTFBAS_BR.rp.TableName = "BPTFBAS";
        BPTFBAS_BR.rp.where = "FEE_CODE = :BPRFBAS.KEY.FEE_CODE";
        BPTFBAS_BR.rp.order = "FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFBAS, this, BPTFBAS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READNEXT_BPTFBAS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFBAS, this, BPTFBAS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_BPTFBAS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFBAS_BR);
    }
    public void R000_READ_BPTFBAS_NO() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTFBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_READNEXT_BPTFBAS();
            if (pgmRtn) return;
            T000_ENDBR_BPTFBAS();
            if (pgmRtn) return;
            BPCTFBAS.RETURN_INFO = 'F';
        } else {
            T000_ENDBR_BPTFBAS();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTFBAS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        BPTFBAS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFBAS, BPTFBAS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFBAS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTFBAS_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111111");
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        BPTFBAS_RD.upd = true;
        IBS.READ(SCCGWA, BPRFBAS, BPTFBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FARM_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FARM_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        IBS.REWRITE(SCCGWA, BPRFBAS, BPTFBAS_RD);
        CEP.TRC(SCCGWA, "TESTWJ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFBAS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTFBAS() throws IOException,SQLException,Exception {
        BPTFBAS_RD = new DBParm();
        BPTFBAS_RD.TableName = "BPTFBAS";
        IBS.DELETE(SCCGWA, BPRFBAS, BPTFBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFBAS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFBAS.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTFBAS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTFBAS = ");
            CEP.TRC(SCCGWA, BPCTFBAS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
