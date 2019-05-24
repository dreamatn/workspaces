package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFHSA {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTFHISA_BR = new brParm();
    DBParm BPTFHISA_RD;
    boolean pgmRtn = false;
    String PGM_BPZIFHSA = "BPZIFHSA";
    String TBL_BPTFHISA = "BPTFHISA";
    short WS_JRN_SEQ = 0;
    BPZIFHSA_WS_KEY_TEXT WS_KEY_TEXT = new BPZIFHSA_WS_KEY_TEXT();
    char WS_TBL_FHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFHISA BPRFHISA = new BPRFHISA();
    SCCGWA SCCGWA;
    BPCIFHSA BPCIFHSA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFHISA BPRFHIS;
    public void MP(SCCGWA SCCGWA, BPCIFHSA BPCIFHSA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFHSA = BPCIFHSA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIFHSA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFHIS = (BPRFHIST) BPCIFHSA.DATA.REC_PT;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFHISA);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHIS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHIS, BPRFHISA);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCIFHSA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.FUNC);
        if (BPCIFHSA.DATA.FUNC == '1') {
            B020_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCIFHSA.DATA.FUNC == '6') {
            B020_STARTBR_PROC_CN();
            if (pgmRtn) return;
        } else if (BPCIFHSA.DATA.FUNC == '2') {
            B030_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCIFHSA.DATA.FUNC == '3') {
            B040_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCIFHSA.DATA.FUNC == '5') {
            B060_READ_FIRST_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZIFHSA INVALID FUNC(" + BPCIFHSA.DATA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHISA);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHISA, BPRFHIS);
    }
    public void B020_STARTBR_PROC() throws IOException,SQLException,Exception {
        BPRFHISA.KEY.AC = BPCIFHSA.DATA.AC;
        BPRFHISA.KEY.CCY = BPCIFHSA.DATA.CCY;
        BPRFHISA.AC_DT = BPCIFHSA.DATA.AC_DT;
        T000_STARTBR_BPTFHISA();
        if (pgmRtn) return;
    }
    public void B020_STARTBR_PROC_CN() throws IOException,SQLException,Exception {
        BPRFHISA.KEY.AC = BPCIFHSA.DATA.AC;
        BPRFHISA.KEY.CCY = BPCIFHSA.DATA.CCY;
        BPRFHISA.KEY.CCY_TYP = BPCIFHSA.DATA.CCY_TYP;
        BPRFHISA.AC_DT = BPCIFHSA.DATA.AC_DT;
        T000_STARTBR_BPTFHISA_CN();
        if (pgmRtn) return;
    }
    public void B060_READ_FIRST_PROC() throws IOException,SQLException,Exception {
        BPRFHISA.KEY.AC = BPCIFHSA.DATA.AC;
        BPRFHISA.KEY.CCY = BPCIFHSA.DATA.CCY;
        BPRFHISA.AC_DT = BPCIFHSA.DATA.AC_DT;
        WS_KEY_TEXT.WS_TS_AC_DT = BPRFHISA.AC_DT;
        WS_KEY_TEXT.WS_TS_AC = BPRFHISA.KEY.AC;
        WS_KEY_TEXT.WS_TS_CCY = BPRFHISA.KEY.CCY;
        T000_READ_FIRST_BPTFHISA();
        if (pgmRtn) return;
    }
    public void B030_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHISA();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFHISA();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTFHISA() throws IOException,SQLException,Exception {
        BPTFHISA_BR.rp = new DBParm();
        BPTFHISA_BR.rp.TableName = "BPTFHISA";
        BPTFHISA_BR.rp.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND AC_DT < :BPRFHISA.AC_DT";
        BPTFHISA_BR.rp.order = "AC_DT DESC";
        IBS.STARTBR(SCCGWA, BPRFHISA, this, BPTFHISA_BR);
    }
    public void T000_STARTBR_BPTFHISA_CN() throws IOException,SQLException,Exception {
        BPTFHISA_BR.rp = new DBParm();
        BPTFHISA_BR.rp.TableName = "BPTFHISA";
        BPTFHISA_BR.rp.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND CCY_TYP = :BPRFHISA.KEY.CCY_TYP "
            + "AND AC_DT < :BPRFHISA.AC_DT";
        BPTFHISA_BR.rp.order = "AC_DT DESC";
        IBS.STARTBR(SCCGWA, BPRFHISA, this, BPTFHISA_BR);
    }
    public void T000_READ_FIRST_BPTFHISA() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND AC_DT <= :BPRFHISA.AC_DT";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCIFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ BPTFHISA ERR:" + IBS.CLS2CPY(SCCGWA, WS_KEY_TEXT);
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READNEXT_BPTFHISA() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHISA, this, BPTFHISA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCIFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ NEXT BPTFHISA ERR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_BPTFHISA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHISA_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIFHSA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIFHSA = ");
            CEP.TRC(SCCGWA, BPCIFHSA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
