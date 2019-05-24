package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSTHD {
    int JIBS_tmp_int;
    DBParm BPTSTHD_RD;
    brParm BPTSTHD_BR = new brParm();
    String K_PGM_NAME = "BPZRSTHD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSTHD BPRSTHD = new BPRSTHD();
    SCCGWA SCCGWA;
    BPCRSTHD BPCRSTHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRSTHD BPCRSTHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSTHD = BPCRSTHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRSTHD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRSTHD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRSTHD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRSTHD.DAT_LEN), BPRSTHD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSTHD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRSTHD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRSTHD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRSTHD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRSTHD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else if (BPCRSTHD.FUNC == 'S') {
            B060_STARTBR_BY_TYPE_PROC();
        } else if (BPCRSTHD.FUNC == 'N') {
            B070_READNEXT_PROC();
        } else if (BPCRSTHD.FUNC == 'E') {
            B080_ENDBR_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRSTHD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSTHD);
        LK_DATA = JIBS_tmp_str[0];
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTSTHD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSTHD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSTHD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSTHD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSTHD_UPD();
    }
    public void B060_STARTBR_BY_TYPE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TYPE();
    }
    public void B070_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTSTHD();
    }
    public void B080_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTSTHD();
    }
    public void T000_READ_BPTSTHD() throws IOException,SQLException,Exception {
        BPTSTHD_RD = new DBParm();
        BPTSTHD_RD.TableName = "BPTSTHD";
        IBS.READ(SCCGWA, BPRSTHD, BPTSTHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STHD (" + IBS.CLS2CPY(SCCGWA, BPRSTHD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTSTHD() throws IOException,SQLException,Exception {
        BPTSTHD_RD = new DBParm();
        BPTSTHD_RD.TableName = "BPTSTHD";
        IBS.WRITE(SCCGWA, BPRSTHD, BPTSTHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STHD (" + IBS.CLS2CPY(SCCGWA, BPRSTHD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTSTHD_UPD() throws IOException,SQLException,Exception {
        BPTSTHD_RD = new DBParm();
        BPTSTHD_RD.TableName = "BPTSTHD";
        BPTSTHD_RD.upd = true;
        IBS.READ(SCCGWA, BPRSTHD, BPTSTHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STHD (" + IBS.CLS2CPY(SCCGWA, BPRSTHD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTSTHD() throws IOException,SQLException,Exception {
        BPTSTHD_RD = new DBParm();
        BPTSTHD_RD.TableName = "BPTSTHD";
        IBS.REWRITE(SCCGWA, BPRSTHD, BPTSTHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STHD (" + IBS.CLS2CPY(SCCGWA, BPRSTHD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTSTHD() throws IOException,SQLException,Exception {
        BPTSTHD_RD = new DBParm();
        BPTSTHD_RD.TableName = "BPTSTHD";
        IBS.DELETE(SCCGWA, BPRSTHD, BPTSTHD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STHD (" + IBS.CLS2CPY(SCCGWA, BPRSTHD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_BY_TYPE() throws IOException,SQLException,Exception {
        BPTSTHD_BR.rp = new DBParm();
        BPTSTHD_BR.rp.TableName = "BPTSTHD";
        BPTSTHD_BR.rp.where = "STC_TYP = :BPRSTHD.STC_TYP";
        BPTSTHD_BR.rp.order = "AC_DATE,CI_NO,SEQ_NO";
        IBS.STARTBR(SCCGWA, BPRSTHD, this, BPTSTHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSTHD.RC_CODE = '1';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSTHD.RC_CODE = '2';
        }
    }
    public void T000_READNEXT_BPTSTHD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRSTHD, this, BPTSTHD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSTHD.RC_CODE = '1';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSTHD.RC_CODE = '2';
        }
    }
    public void T000_ENDBR_BPTSTHD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTSTHD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSTHD.RC_CODE != '1') {
            CEP.TRC(SCCGWA, "BPCRSTHD = ");
            CEP.TRC(SCCGWA, BPCRSTHD);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
