package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSIQAC {
    DBParm DCTIAACR_RD;
    DBParm DCTSPAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUT_FMT = "DCA05";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCOIQAC DCCOIQAC = new DCCOIQAC();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSIQAC DCCSIQAC;
    public void MP(SCCGWA SCCGWA, DCCSIQAC DCCSIQAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSIQAC = DCCSIQAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSIQAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_DATA();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIQAC.AC);
        CEP.TRC(SCCGWA, DCCSIQAC.SEQ);
    }
    public void B020_GET_DATA() throws IOException,SQLException,Exception {
        if (DCCSIQAC.AC.trim().length() > 0 
            && DCCSIQAC.SEQ == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQ_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIQAC.AC.trim().length() > 0 
            && DCCSIQAC.SEQ != 0) {
            IBS.init(SCCGWA, DCRIAACR);
            DCRIAACR.KEY.VIA_AC = DCCSIQAC.AC;
            DCRIAACR.KEY.SEQ = DCCSIQAC.SEQ;
            T00_READ_DCTIAACR();
            if (pgmRtn) return;
        }
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOIQAC);
        DCCOIQAC.IA_AC = DCRIAACR.KEY.VIA_AC;
        DCCOIQAC.SEQ = DCRIAACR.KEY.SEQ;
        DCCOIQAC.SUB_AC = DCRIAACR.SUB_AC;
        if (DCRIAACR.STD_AC_FLG == 'N') {
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.STD_AC = DCRIAACR.SUB_AC;
            T000_READ_DCTSPAC();
            if (pgmRtn) return;
            DCCOIQAC.SUB_AC = DCRSPAC.KEY.FREE_AC;
        }
        DCCOIQAC.FRM_APP = DCRIAACR.FRM_APP;
        DCCOIQAC.USAGE = DCRIAACR.USAGE;
        DCCOIQAC.CCY = DCRIAACR.CCY;
        DCCOIQAC.CCY_TYPE = DCRIAACR.CCY_TYPE;
        DCCOIQAC.DEFAULT_FLG = DCRIAACR.DEFAULT_FLG;
        DCCOIQAC.VCH_NO = DCRIAACR.VCH_NO;
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCOIQAC;
        SCCFMT.DATA_LEN = 158;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T00_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.where = "STD_AC = :DCRSPAC.STD_AC";
        DCTSPAC_RD.fst = true;
        IBS.READ(SCCGWA, DCRSPAC, this, DCTSPAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FREE_AC_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
